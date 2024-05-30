package com.rma.expensetracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rma.expensetracker.data.repositories.CurrentUserRepository
import com.rma.expensetracker.presentation.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    companion object { val currentUserRepository = CurrentUserRepository() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                MainComposable()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseTrackerTheme {
        Greeting("Android")
    }
}

/*
// A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
 */