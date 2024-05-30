package com.rma.expensetracker.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import com.rma.expensetracker.presentation.postlogin.GreetingScreen

fun NavGraphBuilder.postLoginNavGraph(navController: NavHostController) {

    navigation(
        startDestination = PostLoginDestinations.GreetingScreen.destination,
        route = PostLoginDestinations.Root.destination
    ) {
        composable(
            route = PostLoginDestinations.GreetingScreen.destination,
            arguments = PostLoginDestinations.GreetingScreen.arguments
        ) {
            GreetingScreen()
        }
    }
}
