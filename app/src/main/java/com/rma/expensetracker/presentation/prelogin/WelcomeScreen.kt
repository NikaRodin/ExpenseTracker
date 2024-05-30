package com.rma.expensetracker.presentation.prelogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rma.expensetracker.R

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeScreenViewModel = WelcomeScreenViewModel(navController)
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var isLoginFormDisplayed by rememberSaveable { mutableStateOf(true) }

    val cardTitle = stringResource(if(isLoginFormDisplayed) R.string.sign_in else R.string.register_user)
    val linkText = stringResource(if(isLoginFormDisplayed) R.string.not_registered_yet else R.string.already_have_acc)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .height(screenHeight / 4)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = stringResource(id = R.string.logo_content_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(percent = 50)) //50 je krug, ili koristi CircleShape
                    .border(2.dp, Color.Gray, RoundedCornerShape(percent = 50))
            )

            Text(
                text = stringResource(R.string.welcome),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
        ){
            Column(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .padding(10.dp)
            ){
                Row (modifier = Modifier.padding(10.dp)) {
                    Text(text = cardTitle, fontWeight = FontWeight.Bold)
                }
                WelcomeScreenCardContent(isLoginFormDisplayed, viewModel)
            }
        }

        TextButton(onClick = { isLoginFormDisplayed = !isLoginFormDisplayed }) {
            Text(text = linkText, textDecoration = TextDecoration.Underline)
        }
    }
}

@Composable
fun WelcomeScreenCardContent(
    isLoginFormDisplayed: Boolean,
    viewModel: WelcomeScreenViewModel
) {
    if(isLoginFormDisplayed) {
        LoginForm(viewModel)
    } else {
        RegistrationForm(viewModel)
    }
}