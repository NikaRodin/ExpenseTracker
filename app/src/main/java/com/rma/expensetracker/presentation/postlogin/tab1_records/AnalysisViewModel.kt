package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.SelectedAccount
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.Category
import com.rma.expensetracker.data.models.useful.Record
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnalysisViewModel : ViewModel() {
    private val accountId: StateFlow<String?> = SelectedAccount.selectedAccountId

    private val _currentAccount = MutableStateFlow<Account?>(null)
    val currentAccount: StateFlow<Account?> = _currentAccount

    private val _recordsList = MutableStateFlow(emptyList<Record>())
    val recordsList: StateFlow<List<Record>> = _recordsList

    private val _categoriesList = MutableStateFlow(emptyList<Category>())
    val categoriesList: StateFlow<List<Category>> = _categoriesList

    init {
        //showLoading()
        BottomNavBarIndicator.hideBottomNavBar()
        resetAnalysisScreen()
    }

    private fun resetAnalysisScreen() {
        _currentAccount.value = accountId.value?.let { AccountInteractor.getAccountById(it) }

        val records = _currentAccount.value?.let {
            RecordInteractor.getRecordsByAccountId(it.id)
        }
        _recordsList.value = records?: emptyList()

        val categories = CurrentUser.currentUser.value?.let {
            CategoryInteractor.getCategoriesByUserId(it.id)
        }
        _categoriesList.value = categories?: emptyList()
    }
}