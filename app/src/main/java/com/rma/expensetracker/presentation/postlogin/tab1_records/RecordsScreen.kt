package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.presentation.components.ActionBarWithBalance
import com.rma.expensetracker.presentation.components.GalleryIcon
import com.rma.expensetracker.presentation.components.PagerComponent
import com.rma.expensetracker.presentation.components.RecordCard
import com.rma.expensetracker.presentation.components.SettingsIcon
import com.rma.expensetracker.presentation.components.TopAppBarLayout
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreen(
    navController: NavHostController,
    viewModel: RecordsViewModel = viewModel()
) {
    val listState = rememberLazyListState()
    val accountsList by viewModel.accountsList.collectAsState()
    val recordsList by viewModel.recordsList.collectAsState()

    val pagerState = rememberPagerState(pageCount = { accountsList.size })
    val currentAccountIndex = remember { mutableIntStateOf(0) }

    LaunchedEffect(true) {
        BottomNavBarIndicator.showBottomNavBar()
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { pageIndex ->
            currentAccountIndex.intValue = pageIndex

            if(accountsList.isNotEmpty())
                viewModel.updateRecordsList(accountsList[pageIndex].id)
        }
    }

    TopAppBarLayout(
        screenTitle = stringResource(R.string.my_accounts),
        navigationIcons = { GalleryIcon(navController) },
        actions = { SettingsIcon(navController) }
    ) { innerPadding ->

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            // Top part with the graph
            item {
                if(accountsList.isEmpty()) {
                    Text("Nema računa")
                } else {
                    PagerComponent(pagerState, accountsList) {
                        /*TODO*/
                    }
                }
            }

            // Sticky action bar
            val balance = if(accountsList.isEmpty()) {
                "?"
            } else {
                String.format(
                    Locale.US,
                    "%.2f",
                    accountsList[currentAccountIndex.intValue].balance
                )
            }

            stickyHeader {
                Column( modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                ) {
                    ActionBarWithBalance(
                        balance =  balance,
                        leadingIcons = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(R.string.search_records_icon_description)
                                )
                            }
                        },
                        trailingIcons = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_filter_list_24),
                                    contentDescription = stringResource(R.string.filter_list_icon_description)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_filter_alt_24),
                                    contentDescription = stringResource(R.string.filter_icon_description)
                                )
                            }
                        }
                    )
                }
            }

            // Scrollable list of cards
            items(recordsList) { record ->
                RecordCard(record = record) {
                    viewModel.onRecordClicked(record.id, navController)
                }
            }
        }
    }
}