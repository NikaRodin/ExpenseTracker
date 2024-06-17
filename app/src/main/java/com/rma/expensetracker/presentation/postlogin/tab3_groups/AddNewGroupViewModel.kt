package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.LoadingState
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.interactors.UserInteractor
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Category
import com.rma.expensetracker.data.models.useful.RecordMock
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class AddNewGroupViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val _allUsersList = MutableStateFlow(emptyList<User>())

    private val _selectedUsersList = MutableStateFlow(emptyList<User>())
    val selectedUsersList: StateFlow<List<User>> = _selectedUsersList

    private val _isTypeMenuExpanded = MutableStateFlow(false)
    val isTypeMenuExpanded: StateFlow<Boolean> = _isTypeMenuExpanded

    private val _isExpense = MutableStateFlow(true)
    val isExpense: StateFlow<Boolean> = _isExpense

    private val _titleState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onTitleTextChange
        )
    )

    private val _amountState = MutableStateFlow(
        NumericalInputFieldState(
            value = "0.00",
            onValueChange = this::onAmountChange
        )
    )

    private val _searchQueryState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onSearchQueryChange
        )
    )


    val titleState: StateFlow<InputFieldState> = _titleState
    val amountState: StateFlow<NumericalInputFieldState> = _amountState
    val searchQueryState: StateFlow<InputFieldState> = _searchQueryState

    init {
        //showLoading()
        resetScreen()
        BottomNavBarIndicator.hideBottomNavBar()
    }

    private fun resetScreen() {
        LoadingState.showLoading()
        viewModelScope.launch {
            _selectedUsersList.value = emptyList()
            _allUsersList.value = UserInteractor.getAllUsers()
            LoadingState.stopLoading()
        }
    }

    fun onDismissClicked(navController: NavController) {
        navController.popBackStack()
        BottomNavBarIndicator.showBottomNavBar()
    }

    fun onSaveClicked(navController: NavController) {
        LoadingState.showLoading()
        viewModelScope.launch {
            if(_currentUser.value != null) {
                if(titleState.value.text.isBlank()) {
                    ToastState.triggerToast("Naziv ne smije biti prazan!")
                    LoadingState.stopLoading()
                    return@launch
                }

                var enteredAmount = amountState.value.value
                if (enteredAmount.isBlank() || !enteredAmount.contains("[0-9]".toRegex())) {
                    enteredAmount = "0.00"
                }
                var newAmount = enteredAmount.toDouble()
                newAmount = if(isExpense.value && newAmount != 0.00) -newAmount else newAmount

                val usersToBeAdded: MutableList<String> = _selectedUsersList.value.map {
                        user -> user.id
                }.toMutableList()

                /*if(!usersToBeAdded.contains(_currentUser.value!!.id)) {
                    usersToBeAdded += _currentUser.value!!.id
                }*/

                if(usersToBeAdded.contains(_currentUser.value!!.id)) {
                    usersToBeAdded -= _currentUser.value!!.id
                }

                val newAccId = UUID.randomUUID().toString()
                val newAccount = Account(
                    id = newAccId,
                    balance = 0.0,
                    title = _titleState.value.text,
                    isGroupAccount = true
                )
                val successAcc = AccountInteractor.addAccount(usersToBeAdded, newAccount)
                if(!successAcc) {
                    ToastState.triggerToast("Pogreška pri stvaranju računa.")
                    LoadingState.stopLoading()
                    return@launch
                }

                if(!newAmount.equals(0.00)) {
                    var newCategory = CategoryInteractor.findCategory(
                        _currentUser.value!!.id, "početni saldo"
                    )

                    if(newCategory == null) {
                        newCategory = Category(
                            id = UUID.randomUUID().toString(),
                            title = "početni saldo",
                            userId = _currentUser.value!!.id
                        )
                        val successCat = CategoryInteractor.addCategory(newCategory)
                        if(!successCat) {
                            ToastState.triggerToast("Pogreška pri stvaranju kategorije.")
                            LoadingState.stopLoading()
                            return@launch
                        }
                    }

                    val successRec = RecordInteractor.addRecord(
                        RecordMock(
                            id = UUID.randomUUID().toString(),
                            title = "Otvaranje računa",
                            amount = newAmount,
                            date = LocalDate.now(),
                            isGroupRecord = false,
                            userId = _currentUser.value!!.id,
                            accountId = newAccId,
                            categoryId = newCategory.id
                        )
                    )

                    if(!successRec) {
                        ToastState.triggerToast("Pogreška pri stvaranju početnog salda.")
                        LoadingState.stopLoading()
                        return@launch
                    }
                }

                ToastState.triggerToast("Grupni račun dodan.")
                onDismissClicked(navController)
                LoadingState.stopLoading()
            } else {
                ToastState.triggerToast("Greška")
                LoadingState.stopLoading()
            }
        }
    }

    private fun onTitleTextChange(newValue: String) {
        _titleState.update { it.copy(text = newValue) }
    }
    fun onAmountChange(newValue: String) {
        _amountState.update { it.copy(value = newValue) }
    }

    private fun onSearchQueryChange(newValue: String) {
        _searchQueryState.update { it.copy(text = newValue) }
    }

    fun onTypeMenuArrowClicked() {
        _isTypeMenuExpanded.value = !_isTypeMenuExpanded.value
    }

    fun onRecordTypeSelected(typeIndex: Int) {
        _isExpense.value = (typeIndex == 0)
        onTypeMenuArrowClicked()
    }

    fun onUserSelected(user: User) {
        if (!_selectedUsersList.value.contains(user)) {
            _selectedUsersList.value += user
        }
    }

    fun onUserDeselected(user: User) {
        _selectedUsersList.value = _selectedUsersList.value.filter {
            it.id != user.id
        }
    }

    fun getFilteredUsers(): List<User> {
        val query = _searchQueryState.value.text.lowercase()
        return if (query.isBlank()) {
            emptyList()
        } else {
            _allUsersList.value.filter {
                it.firstName.lowercase().contains(query)
                || it.lastName.lowercase().contains(query)
                || it.email.lowercase().contains(query)
                || it.userName?.lowercase()?.contains(query) ?: false
            }
        }
    }
}