package com.rma.expensetracker.presentation.prelogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.TextVisibilityIcon

@Composable
fun LoginForm(viewModel: WelcomeScreenViewModel) {
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

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

        Button(onClick = viewModel::login) {
            Text (stringResource(R.string.next))
        }
    }
}