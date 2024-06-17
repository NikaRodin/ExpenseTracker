package com.rma.expensetracker.presentation.prelogin

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.AuthenticationInteractor.userLogin
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WelcomeScreenViewModel : ViewModel(){

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
            text = "anne.anderson@mail.com",
            onTextChange = this::onEmailTextChange
        )
    )
    private val _passwordState = MutableStateFlow(
        InputFieldState(
            text = "12345",
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
        /*viewModelScope.launch {
            AuthenticationInteractor.dispatchLoginRequest(
                emailState.value.text,
                passwordState.value.text
            )
        }*/
        val success = userLogin(emailState.value.text, passwordState.value.text)
        if(!success) ToastState.triggerToast("Prijava neuspješna")
    }
    fun registerUser() {
        /*
        if(passwordState.value.text == repeatPasswordState.value.text)
            viewModelScope.launch {
                AuthenticationInteractor.dispatchRegistrationRequest(
                    email = emailState.value.text,
                    password = passwordState.value.text,
                    firstName = firstNameState.value.text,
                    lastName = lastNameState.value.text,
                    userName = usernameState.value.text
                )
            }
        else {}
        */
    }
}