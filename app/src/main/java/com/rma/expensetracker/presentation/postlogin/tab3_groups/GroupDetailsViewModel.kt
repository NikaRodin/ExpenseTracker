package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.SelectedGroupAccount
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.UserInteractor
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GroupDetailsViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val groupAccountId: StateFlow<String?> = SelectedGroupAccount.selectedGroupAccountId

    private val _currentGroupAccount = MutableStateFlow<Account?>(null)
    val currentGroupAccount: StateFlow<Account?> = _currentGroupAccount

    private val _groupUsersList = MutableStateFlow(emptyList<User>())
    val groupUsersList: StateFlow<List<User>> = _groupUsersList

    private val _allUsersList = MutableStateFlow(emptyList<User>())
    val allUsersList: StateFlow<List<User>> = _allUsersList

    private val _selectedUsersList = MutableStateFlow(emptyList<User>())   //User ili nešto drugo?
    val selectedUsersList: StateFlow<List<User>> = _selectedUsersList

    private val _usersOnEditScreen = MutableStateFlow(emptyList<User>())   //User ili nešto drugo?
    val usersOnEditScreen: StateFlow<List<User>> = _usersOnEditScreen

    private val _isEditModeOn = MutableStateFlow(false)
    val isEditModeOn: StateFlow<Boolean> = _isEditModeOn

    private val _isAddUserDialogOpen = MutableStateFlow(false)
    val isAddUserDialogOpen: StateFlow<Boolean> = _isAddUserDialogOpen

    private val _isLeaveGroupDialogOpen = MutableStateFlow(false)
    val isLeaveGroupDialogOpen: StateFlow<Boolean> = _isLeaveGroupDialogOpen

    private val _isRemovePersonDialogOpen = MutableStateFlow(false)
    val isRemovePersonDialogOpen: StateFlow<Boolean> = _isRemovePersonDialogOpen

    private val _removedPersonId = MutableStateFlow<String?>(null)
    val removedPersonId: StateFlow<String?> = _removedPersonId

    private val _titleState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onTitleTextChange
        )
    )

    val titleState: StateFlow<InputFieldState> = _titleState

    init {
        //showLoading()
        BottomNavBarIndicator.hideBottomNavBar()
        resetPreviewScreen()
        resetEditScreen()
    }

    private fun resetPreviewScreen() {
        _currentGroupAccount.value = groupAccountId.value?.let {
            AccountInteractor.getAccountById(it)
        }
        val groupUsers = groupAccountId.value?.let {
            UserInteractor.getUsersByAccountId(it)
        }
        _groupUsersList.value = groupUsers?: emptyList()
    }

    fun resetEditScreen() {
        _currentGroupAccount.value?.let {
            onTitleTextChange(it.title)
        }
        _usersOnEditScreen.value = _groupUsersList.value
        _selectedUsersList.value = emptyList()
        _allUsersList.value = UserInteractor.getAllUsers()
    }

    fun onEditClicked() { _isEditModeOn.value = true }

    fun onDismissClicked() { _isEditModeOn.value = false }

    fun onSaveClicked() {
        // resetiraj preview screen
    }

    fun onLeaveGroupClicked() {
        _isLeaveGroupDialogOpen.value = true
    }

    fun onLeaveGroupDismissed() {
        _isLeaveGroupDialogOpen.value = false
    }

    fun onLeaveGroupConfirmed(navController: NavController) {
        onLeaveGroupDismissed()
        groupAccountId.value?.let {
            //Remove user from group account -> provjeri ima li ih dovoljno?
            ToastState.triggerToast("Napustili ste grupu.")
            navController.navigate(PostLoginDestinations.GroupsScreen.destination) {
                popUpTo(0)
                launchSingleTop = true
            }
        }
    }

    private fun onTitleTextChange(newValue: String) {
        _titleState.update { it.copy(text = newValue) }
    }

    fun onAddPersonClicked() {
        _isAddUserDialogOpen.value = true
    }

    fun onAddPersonDismissed() {
        _isAddUserDialogOpen.value = false
    }

    fun onAddPersonSaved() {
        onAddPersonDismissed()
        /*TODO*/
    }

    fun onRemovePersonClicked(userId: String) {
        _isRemovePersonDialogOpen.value = true
        _removedPersonId.value = userId
    }

    fun onRemovePersonDismissed() {
        _isRemovePersonDialogOpen.value = false
        _removedPersonId.value = null
    }

    fun onRemovePersonConfirmed() {
        _usersOnEditScreen.value = _usersOnEditScreen.value.filter {
            it.id != _removedPersonId.value
        }
        onRemovePersonDismissed()
    }

    fun showWarningToast(message: String) {
        ToastState.triggerToast(message)
    }
}