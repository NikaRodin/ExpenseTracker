package com.rma.expensetracker.presentation.navigation.directions

import androidx.navigation.NamedNavArgument

object PostLoginDestinations {

    val Root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "post-login"
    }

    val RecordsScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "records-screen"
    }

    val SavingsScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "savings-screen"
    }

    val AddNewRecordScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "add-new-record-screen"
    }

    val GroupsScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "groups-screen"
    }

    val BudgetScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "budget-screen"
    }

    // Example of a route with arguments
//    object User {
//        const val KEY_USER_ID = "userId"
//        const val destination: String = "user/{$KEY_USER_ID}"
//
//        val definedArguments: List<NamedNavArgument> = listOf(
//            navArgument(KEY_USER_ID) { type = NavType.IntType }
//        )
//
//        fun userRoute(userId: Int) = object : NavigationCommand {
//            override val arguments: List<NamedNavArgument>
//                get() = definedArguments
//            override val destination: String
//                get() = "user/$userId"
//        }
//    }

   /* val ApiInfo = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "apiInfo"
    }*/
}