package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.LoadingState
import com.rma.expensetracker.common.SelectedGroupAccount
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AccountInteractor
import com.rma.expensetracker.data.interactors.UserInteractor
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GroupDetailsViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val groupAccountId: StateFlow<String?> = SelectedGroupAccount.selectedGroupAccountId

    private val _currentGroupAccount = MutableStateFlow<Account?>(null)
    val currentGroupAccount: StateFlow<Account?> = _currentGroupAccount

    private val _groupUsersList = MutableStateFlow(emptyList<User>())
    val groupUsersList: StateFlow<List<User>> = _groupUsersList

    private val _allUsersList = MutableStateFlow(emptyList<User>())

    private val _selectedUsersList = MutableStateFlow(emptyList<User>())
    val selectedUsersList: StateFlow<List<User>> = _selectedUsersList

    private val _usersOnEditScreen = MutableStateFlow(emptyList<User>())
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

    private val _titleState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onTitleTextChange
        )
    )

    private val _searchQueryState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onSearchQueryChange
        )
    )

    val titleState: StateFlow<InputFieldState> = _titleState
    val searchQueryState: StateFlow<InputFieldState> = _searchQueryState

    init {
        BottomNavBarIndicator.hideBottomNavBar()
        resetPreviewScreen()
        resetEditScreen()
    }

    private fun resetPreviewScreen() {
        LoadingState.showLoading()
        viewModelScope.launch {
            _currentGroupAccount.value = groupAccountId.value?.let {
                AccountInteractor.getAccountById(it)
            }
            val groupUsers = groupAccountId.value?.let {
                UserInteractor.getUsersByAccountId(it)
            }
            _groupUsersList.value = groupUsers?: emptyList()
            LoadingState.stopLoading()
        }
    }

    fun resetEditScreen() {
        LoadingState.showLoading()
        viewModelScope.launch {
            _currentGroupAccount.value?.let {
                onTitleTextChange(it.title)
            }
            _usersOnEditScreen.value = _groupUsersList.value
            _selectedUsersList.value = emptyList()
            _allUsersList.value = UserInteractor.getAllUsers()
            LoadingState.stopLoading()
        }
    }

    private fun onTitleTextChange(newValue: String) {
        _titleState.update { it.copy(text = newValue) }
    }

    private fun onSearchQueryChange(newValue: String) {
        _searchQueryState.update { it.copy(text = newValue) }
    }

    fun onEditClicked() { _isEditModeOn.value = true }

    fun onDismissClicked() { _isEditModeOn.value = false }

    fun onSaveClicked() {
        LoadingState.showLoading()
        viewModelScope.launch {
            if(_currentGroupAccount.value != null) {
                if(titleState.value.text.isBlank()) {
                    ToastState.triggerToast("Naziv ne smije biti prazan!")
                    LoadingState.stopLoading()
                    return@launch
                }

                val successUpdate = AccountInteractor.updateAccount(
                    accId = _currentGroupAccount.value!!.id,
                    newAccount = _currentGroupAccount.value!!.copy(title = titleState.value.text)
                )

                if(!successUpdate) {
                    ToastState.triggerToast("Pogreška prilikom ažuriranja računa.")
                    LoadingState.stopLoading()
                    return@launch
                }

                val usersToBeRemoved: MutableList<User> = mutableListOf()
                usersToBeRemoved.addAll(_groupUsersList.value)
                usersToBeRemoved.removeAll(_usersOnEditScreen.value)

                val successRemove = AccountInteractor.removeUsersFromAccount(
                    usersToBeRemoved.map { user -> user.id },
                    _currentGroupAccount.value!!.id
                )

                if(!successRemove) {
                    ToastState.triggerToast("Pogreška prilikom uklanjanja korisnika.")
                    LoadingState.stopLoading()
                    return@launch
                }

                val usersToBeAdded: MutableList<User> = mutableListOf()
                usersToBeAdded.addAll(_usersOnEditScreen.value)
                usersToBeAdded.removeAll(_groupUsersList.value)

                val successAdd = AccountInteractor.addUsersToAccount(
                    usersToBeAdded.map { user -> user.id },
                    _currentGroupAccount.value!!.id
                )

                if(!successAdd) {
                    ToastState.triggerToast("Pogreška prilikom dodavanja korisnika.")
                    LoadingState.stopLoading()
                    return@launch
                }

                resetPreviewScreen()
                onDismissClicked()
                LoadingState.stopLoading()
            } else {
                ToastState.triggerToast("Greška")
                LoadingState.stopLoading()
            }
        }
    }

    fun onLeaveGroupClicked() {
        _isLeaveGroupDialogOpen.value = true
    }

    fun onLeaveGroupDismissed() {
        _isLeaveGroupDialogOpen.value = false
    }

    fun onLeaveGroupConfirmed(navController: NavController) {
        LoadingState.showLoading()
        viewModelScope.launch {
            if(groupAccountId.value != null && _currentUser.value != null){
                val successRemove = AccountInteractor.removeUsersFromAccount(
                    listOf(_currentUser.value!!.id),
                    groupAccountId.value!!
                )

                if(!successRemove) {
                    ToastState.triggerToast("Napuštanje grupe nije uspjelo.")
                    LoadingState.stopLoading()
                    return@launch
                }

                ToastState.triggerToast("Napustili ste grupu.")
                navController.navigate(PostLoginDestinations.GroupsScreen.destination) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            } else {
                ToastState.triggerToast("Greška")
            }
            onLeaveGroupDismissed()
            LoadingState.stopLoading()
        }
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

    /////////////////////////////////////////
    fun onAddPersonClicked() {
        _isAddUserDialogOpen.value = true
    }

    fun onAddPersonDismissed() {
        _isAddUserDialogOpen.value = false
    }

    fun onAddPersonSaved() {
        val updatedEditScreenUsers: MutableSet<User> = mutableSetOf()
        updatedEditScreenUsers.addAll(_usersOnEditScreen.value)
        updatedEditScreenUsers.addAll(_selectedUsersList.value)

        _usersOnEditScreen.value = updatedEditScreenUsers.toList()
        onAddPersonDismissed()
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