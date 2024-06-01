package com.rma.expensetracker.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rma.expensetracker.presentation.navigation.directions.PreLoginDestinations
import com.rma.expensetracker.presentation.prelogin.WelcomeScreen

fun NavGraphBuilder.preLoginNavGraph(navController: NavHostController) {

    navigation(
        startDestination = PreLoginDestinations.WelcomeScreen.destination,  //koji ekran se otvori prvi
        route = PreLoginDestinations.Root.destination
    ) {
        composable(
            route = PreLoginDestinations.WelcomeScreen.destination,
            arguments = PreLoginDestinations.WelcomeScreen.arguments
        ) {
            WelcomeScreen(navController)
        }
    }
}
