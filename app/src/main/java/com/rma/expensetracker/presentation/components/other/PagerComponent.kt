package com.rma.expensetracker.presentation.components.other

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.presentation.components.graphs.LineGraph

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerComponent(
    pagerState: PagerState,
    accountsList: List<Account>,
    recordsList: List<Record>,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clickable { onClick() }
        .background(MaterialTheme.colorScheme.primary)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = accountsList[page].title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                LineGraph(recordsList = recordsList)
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
            }
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if(pagerState.pageCount > 1) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.onBackground
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(9.dp)
                    )
                }
            }
        }
    }
}