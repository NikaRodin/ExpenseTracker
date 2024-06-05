package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.data.models.useful.Record
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordsViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val _accountsList = MutableStateFlow(emptyList<Account>())
    val accountsList: StateFlow<List<Account>> = _accountsList

    private val _recordsList = MutableStateFlow(emptyList<Record>())
    val recordsList: StateFlow<List<Record>> = _recordsList

    init {
        //showLoading()
        getAllCurrentUserAccounts()
        if(accountsList.value.isNotEmpty()) {
            updateRecordsList(accountsList.value[0].id) //MOŽDA NE
        }
    }

    private fun getAllCurrentUserAccounts() {
        val accounts = _currentUser.value?.let { AccountInteractor.getAccountsByUserId(it.id) }
        _accountsList.value = accounts?: emptyList()
    }

    fun updateRecordsList(accId: String) {
        val records = RecordInteractor.getRecordsByAccountId(accId)
        _recordsList.value = records
    }

}