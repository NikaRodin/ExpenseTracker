package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.LoadingState
import com.rma.expensetracker.common.SelectedAccount
import com.rma.expensetracker.common.SelectedRecord
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecordsViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val _accountsList = MutableStateFlow(emptyList<Account>())
    val accountsList: StateFlow<List<Account>> = _accountsList

    private val _recordsList = MutableStateFlow(emptyList<Record>())
    val recordsList: StateFlow<List<Record>> = _recordsList

    init {
        getAllCurrentUserAccounts()
    }

    private fun getAllCurrentUserAccounts() {
        LoadingState.showLoading()
        viewModelScope.launch {
            val accounts = _currentUser.value?.let { AccountInteractor.getAccountsByUserId(it.id) }
            _accountsList.value = accounts?: emptyList()
            LoadingState.stopLoading()
        }
    }

    fun updateRecordsList(accId: String) {
        LoadingState.showLoading()
        viewModelScope.launch {
            getAllCurrentUserAccounts()
            val records = RecordInteractor.getRecordsByAccountId(accId)
            _recordsList.value = records.sortedWith( compareByDescending { it.date })
            LoadingState.stopLoading()
        }
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