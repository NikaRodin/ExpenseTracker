package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.SelectedRecordId
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.presentation.components.InputFieldState
import com.rma.expensetracker.presentation.components.NumericalInputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.format.DateTimeFormatter

class RecordDetailsViewModel : ViewModel() {
    private val recordId: StateFlow<String?> = SelectedRecordId.selectedRecordId

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
        _currentRecord.value = recordId.value?.let { RecordInteractor.getRecordById(it) }
        _currentAccount.value = _currentRecord.value?.let { AccountInteractor.getAccountById(it.accountId) }

        val accounts = CurrentUser.currentUser.value?.let {
            AccountInteractor.getAccountsByUserId(it.id)
        }
        _accountsList.value = accounts?: emptyList()

        resetEditScreen()
        BottomNavBarIndicator.hideBottomNavBar()
    }

    fun resetEditScreen() {
        _currentRecord.value?.let {
            onTitleTextChange(it.title)
        }
        _currentRecord.value?.let {
            onAmountChange(it.amount.toString())
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
    }

    fun onEditClicked() { _isEditModeOn.value = true }

    fun onDeleteClicked() {
        //izbriši record
        //vrati na početni screen
    }

    fun onDimissClicked() { _isEditModeOn.value = false }

    fun onSaveClicked() {
        //updateaj record u bazi
        onDimissClicked()
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
}