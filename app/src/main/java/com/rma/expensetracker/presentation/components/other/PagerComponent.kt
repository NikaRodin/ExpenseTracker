package com.rma.expensetracker.presentation.components.other

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.data.models.mock.Account

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerComponent(
    pagerState: PagerState,
    accountsList: List<Account>,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        HorizontalPager(state = pagerState) { page ->
            GraphComponent(
                accountTitle = accountsList[page].title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if(pagerState.pageCount > 1) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White
                    else Color.DarkGray
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