package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.BackButton
import com.rma.expensetracker.presentation.components.graphs.BarGraph
import com.rma.expensetracker.presentation.components.graphs.LineGraph
import com.rma.expensetracker.presentation.components.graphs.PieGraph
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout

@Composable
fun AnalysisScreen(
    navController: NavHostController,
    viewModel: AnalysisViewModel = viewModel()
) {
    val currentAccount by viewModel.currentAccount.collectAsState()
    val recordsList by viewModel.recordsList.collectAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val expenses = recordsList.filter { it.amount < 0 }
    val incomes = recordsList.filter { it.amount > 0 }

    TopAppBarLayout(
        screenTitle = stringResource(R.string.analysis),
        navigationIcons = {
            BackButton(navController = navController, showBottomNavBar = true)
        }, actions = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            if(currentAccount == null)
                Text(text = stringResource(R.string.record_not_selected))
            else {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = currentAccount!!.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    LineGraph(recordsList = recordsList)
                    Spacer(modifier = Modifier.padding(vertical = 5.dp))
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp)
                ) {
                    if(expenses.isNotEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.size(screenWidth/2)
                        ) {
                            Text(text = stringResource(R.string.all_expenses))
                            PieGraph(expenses)
                        }
                    }

                    if(incomes.isNotEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.size(screenWidth/2)
                        ) {
                            Text(text = stringResource(R.string.all_incomes))
                            PieGraph(incomes)
                        }
                    }
                }

                if(currentAccount!!.isGroupAccount && recordsList.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(8.dp)
                    ) {
                        BarGraph(recordsList)
                    }
                }
            }
        }
    }
}