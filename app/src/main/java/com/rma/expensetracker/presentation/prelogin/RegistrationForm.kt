package com.rma.expensetracker.presentation.prelogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.prelogin.components.TextVisibilityIcon

@Composable
fun RegistrationForm(viewModel: WelcomeScreenViewModel) {
    val firstNameState by viewModel.firstNameState.collectAsState()
    val lastNameState by viewModel.lastNameState.collectAsState()
    val usernameState by viewModel.usernameState.collectAsState()
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()
    val repeatPasswordState by viewModel.repeatPasswordState.collectAsState()
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Column(
            modifier = Modifier
                .height(screenHeight/4)
                .verticalScroll(scrollState)
        ) {
            OutlinedTextField(
                value = firstNameState.text,
                onValueChange = firstNameState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_firstname_field_label)) },
            )

            OutlinedTextField(
                value = lastNameState.text,
                onValueChange = lastNameState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_lastname_field_label)) },
            )

            OutlinedTextField(
                value = usernameState.text,
                onValueChange = usernameState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_username_field_label)) },
            )

            OutlinedTextField(
                value = emailState.text,
                onValueChange = emailState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_email_field_label)) },
            )

            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            OutlinedTextField(
                value = passwordState.text,
                onValueChange = passwordState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_password_field_label)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    TextVisibilityIcon(
                        isVisible = passwordVisible,
                        onVisibilityClicked = { passwordVisible = !passwordVisible })
                }
            )

            var repeatPasswordVisible by rememberSaveable { mutableStateOf(false) }
            OutlinedTextField(
                value = repeatPasswordState.text,
                onValueChange = repeatPasswordState.onTextChange,
                label = { Text(stringResource(id = R.string.login_screen_repeat_password_field_label)) },
                visualTransformation = if (repeatPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    TextVisibilityIcon(
                        isVisible = repeatPasswordVisible,
                        onVisibilityClicked = { repeatPasswordVisible = !repeatPasswordVisible })

                    //Icon()
                }
            )
        }

        Button(onClick = viewModel::registerUser) {
            Text (stringResource(R.string.next))
        }
    }
}