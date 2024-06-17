package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.LoadingState
import com.rma.expensetracker.common.SelectedRecord
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Category
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.data.models.useful.RecordMock
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputFieldState
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale
import kotlin.math.absoluteValue

class RecordDetailsViewModel : ViewModel() {
    private val recordId: StateFlow<String?> = SelectedRecord.selectedRecordId

    private val _currentRecord = MutableStateFlow<Record?>(null)
    val currentRecord: StateFlow<Record?> = _currentRecord

    private val _currentAccount = MutableStateFlow<Account?>(null)
    val currentAccount: StateFlow<Account?> = _currentAccount

    private val _accountsList = MutableStateFlow(emptyList<Account>())
    val accountsList: StateFlow<List<Account>> = _accountsList

    private val _isEditModeOn = MutableStateFlow(false)
    val isEditModeOn: StateFlow<Boolean> = _isEditModeOn

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

    private val _isDeleteDialogOpen = MutableStateFlow(false)
    val isDeleteDialogOpen: StateFlow<Boolean> = _isDeleteDialogOpen

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
        BottomNavBarIndicator.hideBottomNavBar()
        resetPreviewScreen()
        resetEditScreen()
    }

    private fun resetPreviewScreen() {
        LoadingState.showLoading()
        viewModelScope.launch {
            _currentRecord.value = recordId.value?.let { RecordInteractor.getRecordById(it) }
            _currentAccount.value = _currentRecord.value?.let { AccountInteractor.getAccountById(it.accountId) }

            val accounts = CurrentUser.currentUser.value?.let {
                AccountInteractor.getAccountsByUserId(it.id)
            }
            _accountsList.value = accounts?: emptyList()

            val categories = CurrentUser.currentUser.value?.let {
                CategoryInteractor.getCategoriesByUserId(it.id)
            }
            _categoriesList.value = categories?: emptyList()
            LoadingState.stopLoading()
        }
    }

    fun resetEditScreen() {
        _currentRecord.value?.let {
            onTitleTextChange(it.title)
        }
        _currentRecord.value?.let {
            onAmountChange(String.format(Locale.US, "%.2f", it.amount.absoluteValue))
            _isExpense.value  = (it.amount < 0)
        }
        _currentRecord.value?.let {
            onDateChange(it.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        }
        _currentRecord.value?.let {
            it.notes?.let { notes -> onNotesTextChange(notes) }
        }
        if(currentAccount.value != null && accountsList.value.isNotEmpty()){
            _selectedAccount.value = _accountsList.value.indexOfFirst {
                currentAccount.value!!.id == it.id
            }
        }
        _currentRecord.value?.let {
            _category.value = it.category
        }

        if(_category.value != null){
            _selectedCategoryId.value = _category.value!!.id
        }
    }

    fun onEditClicked() { _isEditModeOn.value = true }

    fun onTrashcanClicked() {
        _isDeleteDialogOpen.value = true
    }

    fun onDeleteDismissed() {
        _isDeleteDialogOpen.value = false
    }

    fun onDeleteConfirmed(navController: NavController) {
        LoadingState.showLoading()
        viewModelScope.launch {
            recordId.value?.let {
                val success = RecordInteractor.deleteRecord(it)
                if(!success) {
                    ToastState.triggerToast("Brisanje stavke nije uspjelo.")
                    LoadingState.stopLoading()
                    return@launch
                }
                ToastState.triggerToast("Stavka izbrisana.")
                navController.navigate(PostLoginDestinations.RecordsScreen.destination) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            }
            onDeleteDismissed()
            LoadingState.stopLoading()
        }
    }

    fun onDismissClicked() { _isEditModeOn.value = false }

    fun onSaveClicked() {
        LoadingState.showLoading()
        viewModelScope.launch {
            if(currentRecord.value != null && category.value != null && recordId.value != null) {

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

                if(newAmount.equals(0.00)) {
                    ToastState.triggerToast("Iznos ne smije biti nula!")
                    LoadingState.stopLoading()
                    return@launch
                }

                val newRecord = RecordMock(
                    id = recordId.value!!,
                    title = titleState.value.text,
                    amount = newAmount,
                    date = stringToLocalDate(dateState.value.text)?: LocalDate.now(),
                    isGroupRecord = currentRecord.value!!.isGroupRecord,
                    notes = notesState.value.text,
                    photos = currentRecord.value!!.photos,
                    userId = currentRecord.value!!.user.id,
                    accountId = accountsList.value[selectedAccount.value].id,
                    categoryId = category.value!!.id
                )

                val success = RecordInteractor.updateRecord(recordId.value!!, newRecord)
                if(!success) {
                    ToastState.triggerToast("Ažuriranje stavke nije uspjelo.")
                    LoadingState.stopLoading()
                    return@launch
                }
                resetPreviewScreen()
                onDismissClicked()
            } else {
                ToastState.triggerToast("Greška")
            }
            LoadingState.stopLoading()
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