package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.presentation.components.actions_bars.AddItemActionBar
import com.rma.expensetracker.presentation.components.cards.GroupAccountCard
import com.rma.expensetracker.presentation.components.icons.GalleryIcon
import com.rma.expensetracker.presentation.components.icons.NotificationBellIcon
import com.rma.expensetracker.presentation.components.icons.SettingsIcon
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout

@Composable
fun GroupsScreen(
    navController: NavHostController,
    viewModel: GroupsViewModel = viewModel()
) {
    val listState = rememberLazyListState()
    val groupAccountList by viewModel.groupAccountsList.collectAsState()

    LaunchedEffect(true) {
        BottomNavBarIndicator.showBottomNavBar()
    }

    TopAppBarLayout(
        screenTitle = stringResource(R.string.my_groups),
        navigationIcons = { GalleryIcon(navController) },
        actions = { SettingsIcon(navController) }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            AddItemActionBar(
                addButtonLabel = { Text(stringResource(R.string.add_group_account)) },
                trailingIcons = { NotificationBellIcon(navController) },
                onAddClick = {/*TODO*/}
            )

            if(groupAccountList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_group_accounts),
                    modifier = Modifier.padding(20.dp)
                )
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(groupAccountList) { group ->
                        GroupAccountCard(groupAccount = group) {
                            viewModel.onGroupAccountClicked(group.account.id, navController)
                        }
                    }
                }
            }
        }
    }
}