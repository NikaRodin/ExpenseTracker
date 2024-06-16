package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.SelectedAccount
import com.rma.expensetracker.common.SelectedRecord
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
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
    }

    private fun getAllCurrentUserAccounts() {
        val accounts = _currentUser.value?.let { AccountInteractor.getAccountsByUserId(it.id) }
        _accountsList.value = accounts?: emptyList()
    }

    fun updateRecordsList(accId: String) {
        val records = RecordInteractor.getRecordsByAccountId(accId)
        _recordsList.value = records.sortedWith( compareByDescending { it.date })
    }

    fun onRecordClicked(recordId: String, navController:NavHostController) {
        SelectedRecord.updateSelectedRecordId(recordId)
        navController.navigate(
            PostLoginDestinations.RecordDetailsScreen.destination
        ){
            launchSingleTop = true
        }
    }

    fun onAnalysisClicked(accId: String, navController:NavHostController) {
        SelectedAccount.updateSelectedAccountId(accId)
        navController.navigate(
            PostLoginDestinations.AnalysisScreen.destination
        ){
            launchSingleTop = true
        }
    }
}