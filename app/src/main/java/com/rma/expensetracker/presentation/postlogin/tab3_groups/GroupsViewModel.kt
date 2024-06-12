package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.SelectedGroupAccount
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.UserInteractor
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GroupsViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val _groupAccountsList = MutableStateFlow(emptyList<GroupAccountState>())
    val groupAccountsList: StateFlow<List<GroupAccountState>> = _groupAccountsList

    init {
        //showLoading()
        getAllCurrentUserGroupAccounts()
    }

    private fun getAllCurrentUserGroupAccounts() {
        val accounts = _currentUser.value?.let {
            AccountInteractor.getAccountsByUserId(it.id)
        }

        if(accounts != null) {
            val groupAccounts = mutableListOf<GroupAccountState>()

            accounts.forEach { account ->
                if(account.isGroupAccount) {
                    groupAccounts.add(
                        GroupAccountState(
                            account = account,
                            userCount = UserInteractor.getUserCountByAccountId(account.id)
                        )
                    )
                }
            }
            _groupAccountsList.value = groupAccounts
        }
    }

    fun onAddClicked(navController:NavHostController) {
        navController.navigate(
           PostLoginDestinations.AddNewGroupScreen.destination
        ){
            launchSingleTop = true
        }
    }
    fun onGroupAccountClicked(accountId: String, navController:NavHostController) {
        SelectedGroupAccount.updateSelectedGroupAccountId(accountId)
        navController.navigate(
           PostLoginDestinations.GroupDetailsScreen.destination
        ){
            launchSingleTop = true
        }
    }
}