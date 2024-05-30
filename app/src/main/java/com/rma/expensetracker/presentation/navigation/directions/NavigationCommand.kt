package com.rma.expensetracker.presentation.navigation.directions

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val destination: String
}
