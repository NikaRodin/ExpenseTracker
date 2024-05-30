package com.rma.expensetracker.presentation.navigation.directions

import androidx.navigation.NamedNavArgument

object PreLoginDestinations {

    val Root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "pre-login"
    }

    val WelcomeScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "welcome-screen"
    }
}
