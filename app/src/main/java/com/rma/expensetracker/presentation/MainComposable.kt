package com.rma.expensetracker.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavItem
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations
import com.rma.expensetracker.presentation.navigation.directions.PreLoginDestinations
import com.rma.expensetracker.presentation.navigation.postLoginNavGraph
import com.rma.expensetracker.presentation.navigation.preLoginNavGraph
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainComposable(
    viewModel: MainViewModel = viewModel()
) {
    val navController: NavHostController = rememberNavController()
    val userLoggedIn by viewModel.userLoggedIn.collectAsState()
    val startDestination = if(!userLoggedIn) PreLoginDestinations.Root.destination
                            else PostLoginDestinations.Root.destination

    val bottomNavBarState by viewModel.bottomNavBarState.collectAsState()
    val selectedBottomNavItem by viewModel.selectedBottomNavItem.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomNavBarState.isVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surfaceVariant) {
                    bottomNavBarState.items.forEach { item ->
                        BottomNavigationItem(
                            modifier = Modifier.padding(4.dp),
                            selected = item == selectedBottomNavItem,
                            onClick = {
                                navigateToItemDestination(item, navController)
                                viewModel.onBottomNavElementClicked(item)
                            },
                            icon = {
                                Box(modifier = Modifier
                                    .padding(
                                        start = 4.dp,
                                        top = 4.dp,
                                        end = 4.dp,
                                        bottom = if (item.direction == BottomNavItem.BottomNavDirection.ADD) 4.dp
                                        else 0.dp
                                    )
                                    .background(
                                        color =
                                        if (item == selectedBottomNavItem &&
                                            item.direction != BottomNavItem.BottomNavDirection.ADD
                                        )
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                        else
                                            Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .padding(2.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(
                                                if (item.direction == BottomNavItem.BottomNavDirection.ADD) 50.dp
                                                else 30.dp
                                            ),
                                        painter = painterResource(id = item.icon),
                                        contentDescription = item.contentDescription?.let { stringResource(id = it) }
                                    )
                                }
                            },
                            label = item.label?.let { {
                                    Text(
                                        text = stringResource(id = it),
                                        fontSize = 12.sp,
                                        softWrap = false,
                                        overflow = TextOverflow.Visible
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            NavHost(navController = navController, startDestination = startDestination) {
                preLoginNavGraph()
                postLoginNavGraph(navController)
            }
        }
    }
}

fun navigateToItemDestination(item: BottomNavItem, navController: NavHostController) {
    navController.navigate(
        when(item.direction) {
            BottomNavItem.BottomNavDirection.RECORDS -> PostLoginDestinations.RecordsScreen.destination
            BottomNavItem.BottomNavDirection.SAVINGS -> PostLoginDestinations.SavingsScreen.destination
            BottomNavItem.BottomNavDirection.ADD -> PostLoginDestinations.AddNewRecordScreen.destination
            BottomNavItem.BottomNavDirection.GROUPS -> PostLoginDestinations.GroupsScreen.destination
            BottomNavItem.BottomNavDirection.BUDGET -> PostLoginDestinations.BudgetScreen.destination
        }
    ){
        popUpTo(0)
        launchSingleTop = true
    }
}
//TODO ipak prebaci u viewModel kasnije i izdvoji BottomNavigation kao komponentu