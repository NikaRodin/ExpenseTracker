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

    val RecordDetailsScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "record-details-screen"
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

    val GalleryScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "gallery-screen"
    }

    val SettingsScreen = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "settings-screen"
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

    /*
    object RecordDetailsScreen {
        const val KEY_PLAN_ID = "recordId"
        const val destination: String = "plan/{$KEY_PLAN_ID}"

        val definedArguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_PLAN_ID) { type = NavType.StringType }
        )

        fun recordRoute(recordId: String) = object : NavigationCommand {
            override val arguments: List<NamedNavArgument>
                get() = definedArguments
            override val destination: String
                get() = "record/$recordId"
        }
    }
    */
}