package com.rma.expensetracker.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rma.expensetracker.presentation.navigation.directions.PreLoginDestinations
import com.rma.expensetracker.presentation.prelogin.WelcomeScreen

fun NavGraphBuilder.preLoginNavGraph() {

    navigation(
        startDestination = PreLoginDestinations.WelcomeScreen.destination,
        route = PreLoginDestinations.Root.destination
    ) {
        composable(
            route = PreLoginDestinations.WelcomeScreen.destination,
            arguments = PreLoginDestinations.WelcomeScreen.arguments
        ) {
            WelcomeScreen()
        }
    }
}
