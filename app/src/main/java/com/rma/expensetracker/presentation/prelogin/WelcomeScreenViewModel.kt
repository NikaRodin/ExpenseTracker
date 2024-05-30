package com.rma.expensetracker.presentation.prelogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.rma.expensetracker.data.repositories.dispatchLoginRequest
import com.rma.expensetracker.data.repositories.dispatchRegistrationRequest
import com.rma.expensetracker.presentation.prelogin.components.InputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeScreenViewModel(
    private val navController: NavHostController
) : ViewModel(){

    private val _firstNameState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onFirstNameTextChange
        )
    )
    private val _lastNameState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onLastNameTextChange
        )
    )
    private val _usernameState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onUsernameTextChange
        )
    )
    private val _emailState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onEmailTextChange
        )
    )
    private val _passwordState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onPasswordTextChange
        )
    )
    private val _repeatPasswordState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onRepeatPasswordTextChange
        )
    )

    private fun onFirstNameTextChange(newValue: String) { _firstNameState.update { it.copy(text = newValue) } }
    private fun onLastNameTextChange(newValue: String) { _lastNameState.update { it.copy(text = newValue) } }
    private fun onUsernameTextChange(newValue: String) { _usernameState.update { it.copy(text = newValue) } }
    private fun onEmailTextChange(newValue: String) { _emailState.update { it.copy(text = newValue) } }
    private fun onPasswordTextChange(newValue: String) { _passwordState.update { it.copy(text = newValue) } }
    private fun onRepeatPasswordTextChange(newValue: String) { _repeatPasswordState.update { it.copy(text = newValue) } }

    val firstNameState: StateFlow<InputFieldState> = _firstNameState
    val lastNameState: StateFlow<InputFieldState> = _lastNameState
    val usernameState: StateFlow<InputFieldState> = _usernameState
    val emailState: StateFlow<InputFieldState> = _emailState
    val passwordState: StateFlow<InputFieldState> = _passwordState
    val repeatPasswordState: StateFlow<InputFieldState> = _repeatPasswordState

    fun login() {
        //showLoading()
        viewModelScope.launch {
            dispatchLoginRequest(emailState.value.text, passwordState.value.text)
        }

        /*navController.navigate(PostLoginDestinations.Root.destination) {
            popUpTo(0)
            launchSingleTop = true
        }*/

    }
    fun registerUser() {
        if(passwordState.value.text == repeatPasswordState.value.text)
            viewModelScope.launch {
                dispatchRegistrationRequest(
                    email = emailState.value.text,
                    password = passwordState.value.text,
                    firstName = firstNameState.value.text,
                    lastName = lastNameState.value.text,
                    userName = usernameState.value.text
                )
            }
        else {/*TODO*/}
    }
}