package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.Category
import com.rma.expensetracker.data.models.mock.RecordMock
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.UUID

class AddNewGroupViewModel : ViewModel() {
    private val _accountsList = MutableStateFlow(emptyList<Account>())
    val accountsList: StateFlow<List<Account>> = _accountsList

    private val _isTypeMenuExpanded = MutableStateFlow(false)
    val isTypeMenuExpanded: StateFlow<Boolean> = _isTypeMenuExpanded

    private val _isExpense = MutableStateFlow(true)
    val isExpense: StateFlow<Boolean> = _isExpense

    private val _isAccountMenuExpanded = MutableStateFlow(false)
    val isAccountMenuExpanded: StateFlow<Boolean> = _isAccountMenuExpanded

    private val _selectedAccount = MutableStateFlow(-1)
    val selectedAccount: StateFlow<Int> = _selectedAccount

    private val _categoriesList = MutableStateFlow(emptyList<Category>())
    val categoriesList: StateFlow<List<Category>> = _categoriesList

    private val _category = MutableStateFlow<Category?>(null)
    val category: StateFlow<Category?> = _category

    private val _selectedCategoryId = MutableStateFlow<String?>(null)
    val selectedCategoryId: StateFlow<String?> = _selectedCategoryId

    private val _isCategoryPickerOpen = MutableStateFlow(false)
    val isCategoryPickerOpen: StateFlow<Boolean> = _isCategoryPickerOpen


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

    private val _dateState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onDateChange
        )
    )

    private val _notesState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onNotesTextChange
        )
    )

    val titleState: StateFlow<InputFieldState> = _titleState
    val amountState: StateFlow<NumericalInputFieldState> = _amountState
    val dateState: StateFlow<InputFieldState> = _dateState
    val notesState: StateFlow<InputFieldState> = _notesState

    init {
        //showLoading()
        resetScreen()
        BottomNavBarIndicator.hideBottomNavBar()
    }

    private fun resetScreen() {
        val accounts = CurrentUser.currentUser.value?.let {
            AccountInteractor.getAccountsByUserId(it.id)
        }
        _accountsList.value = accounts?: emptyList()

        val categories = CurrentUser.currentUser.value?.let {
            CategoryInteractor.getCategoriesByUserId(it.id)
        }
        _categoriesList.value = categories?: emptyList()

        if(_category.value != null){
            _selectedCategoryId.value = _category.value!!.id
        }
    }

    fun onDismissClicked(navController: NavController) {
        navController.popBackStack()
        BottomNavBarIndicator.activateLastActiveBottomNavItem()
        BottomNavBarIndicator.showBottomNavBar()
    }

    fun onSaveClicked(navController: NavController) {
        if(CurrentUser.currentUser.value != null) {
            if(titleState.value.text.isBlank()) {
                ToastState.triggerToast("Naziv ne smije biti prazan!")
                return
            }
            if(dateState.value.text.isBlank()) {
                ToastState.triggerToast("Odaberite datum!")
                return
            }
            if(selectedAccount.value == -1) {
                ToastState.triggerToast("Odaberite račun!")
                return
            }
            if(category.value == null) {
                ToastState.triggerToast("Odaberite kategoriju!")
                return
            }

            var enteredAmount = amountState.value.value
            if (enteredAmount.isBlank() || !enteredAmount.contains("[0-9]".toRegex())) {
                enteredAmount = "0.00"
            }
            var newAmount = enteredAmount.toDouble()
            newAmount = if(isExpense.value && newAmount != 0.00) -newAmount else newAmount

            val newRecord = RecordMock(
                id = UUID.randomUUID().toString(),
                title = titleState.value.text,
                amount = newAmount,
                date = stringToLocalDate(dateState.value.text)?: LocalDate.now(),
                isGroupRecord = false,
                notes = notesState.value.text,
                photos = emptyList(),
                userId = CurrentUser.currentUser.value!!.id,
                accountId = accountsList.value[selectedAccount.value].id,
                categoryId = category.value!!.id
            )

            RecordInteractor.addRecord(newRecord)
            ToastState.triggerToast("Stavka dodana.")
            onDismissClicked(navController)
        } else {
            ToastState.triggerToast("Greška")
        }
    }

    private fun onTitleTextChange(newValue: String) {
        _titleState.update { it.copy(text = newValue) }
    }
    fun onAmountChange(newValue: String) {
        _amountState.update { it.copy(value = newValue) }
    }

    fun onDateChange(newValue: String) {
        _dateState.update { it.copy(text = newValue) }
    }

    private fun onNotesTextChange(newValue: String) {
        _notesState.update { it.copy(text = newValue) }
    }

    fun onTypeMenuArrowClicked() {
        _isTypeMenuExpanded.value = !_isTypeMenuExpanded.value
    }

    fun onRecordTypeSelected(typeIndex: Int) {
        _isExpense.value = (typeIndex == 0)
        onTypeMenuArrowClicked()
        //showLoading()
    }

    fun onAccountMenuArrowClicked() {
        _isAccountMenuExpanded.value = !_isAccountMenuExpanded.value
    }

    fun onAccountSelected(accIndex: Int) {
        _selectedAccount.value = accIndex
        onAccountMenuArrowClicked()
        //showLoading()
    }

    fun openCategoryPicker(categoryId: String?) {
        _isCategoryPickerOpen.value = true
        _selectedCategoryId.value = categoryId
    }

    fun closeCategoryPicker() {
        _isCategoryPickerOpen.value = false
    }

    fun onCategorySelected(categoryId: String) {
        _selectedCategoryId.value = categoryId
    }

    fun onCategorySaved() {
        _category.value = categoriesList.value.firstOrNull { it.id == _selectedCategoryId.value }
    }

    private fun stringToLocalDate(dateString: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return try {
            LocalDate.parse(dateString, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

}