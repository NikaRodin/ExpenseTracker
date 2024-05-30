package com.rma.expensetracker.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import com.rma.expensetracker.presentation.navigation.directions.PreLoginDestinations
import com.rma.expensetracker.presentation.navigation.postLoginNavGraph
import com.rma.expensetracker.presentation.navigation.preLoginNavGraph

@Composable
fun MainComposable(
    viewModel: MainViewModel = MainViewModel()
) {
    val navController: NavHostController = rememberNavController()
    val userLoggedIn by viewModel.userLoggedIn.collectAsState()
    val startDestination = if(!userLoggedIn) PreLoginDestinations.Root.destination
                            else PostLoginDestinations.Root.destination

    //TODO padding values se pojave ako zadamo bottomBar
    Scaffold(modifier = Modifier.fillMaxSize(), /*bottomBar TODO*/) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            NavHost(navController = navController, startDestination = startDestination) {
                preLoginNavGraph(navController)
                postLoginNavGraph(navController)
            }
        }
    }
}