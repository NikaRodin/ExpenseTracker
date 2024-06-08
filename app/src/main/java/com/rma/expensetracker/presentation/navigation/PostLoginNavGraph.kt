package com.rma.expensetracker.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import com.rma.expensetracker.presentation.postlogin.AddNewRecordScreen
import com.rma.expensetracker.presentation.postlogin.gallery.GalleryScreen
import com.rma.expensetracker.presentation.postlogin.settings.SettingsScreen
import com.rma.expensetracker.presentation.postlogin.tab1_records.RecordDetailsScreen
import com.rma.expensetracker.presentation.postlogin.tab1_records.RecordsScreen
import com.rma.expensetracker.presentation.postlogin.tab2_savings.SavingsScreen
import com.rma.expensetracker.presentation.postlogin.tab3_groups.GroupsScreen
import com.rma.expensetracker.presentation.postlogin.tab4_budget.BudgetScreen

fun NavGraphBuilder.postLoginNavGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = PostLoginDestinations.RecordsScreen.destination,
        route = PostLoginDestinations.Root.destination
    ) {
        composable(
            route = PostLoginDestinations.RecordsScreen.destination,
            arguments = PostLoginDestinations.RecordsScreen.arguments
        ) {
            RecordsScreen(navController)
        }
        composable(
            route = PostLoginDestinations.RecordDetailsScreen.destination,
            arguments = PostLoginDestinations.RecordDetailsScreen.arguments
        ) {
            RecordDetailsScreen(navController)
        }
        composable(
            route = PostLoginDestinations.SavingsScreen.destination,
            arguments = PostLoginDestinations.SavingsScreen.arguments
        ) {
            SavingsScreen()
        }
        composable(
            route = PostLoginDestinations.AddNewRecordScreen.destination,
            arguments = PostLoginDestinations.AddNewRecordScreen.arguments
        ) {
            AddNewRecordScreen()
        }
        composable(
            route = PostLoginDestinations.GroupsScreen.destination,
            arguments = PostLoginDestinations.GroupsScreen.arguments
        ) {
            GroupsScreen()
        }
        composable(
            route = PostLoginDestinations.BudgetScreen.destination,
            arguments = PostLoginDestinations.BudgetScreen.arguments
        ) {
            BudgetScreen()
        }
        composable(
            route = PostLoginDestinations.GalleryScreen.destination,
            arguments = PostLoginDestinations.GalleryScreen.arguments
        ) {
            GalleryScreen()
        }
        composable(
            route = PostLoginDestinations.SettingsScreen.destination,
            arguments = PostLoginDestinations.SettingsScreen.arguments
        ) {
            SettingsScreen()
        }
    }
}
