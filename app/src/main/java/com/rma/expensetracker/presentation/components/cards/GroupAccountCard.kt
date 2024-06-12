package com.rma.expensetracker.presentation.components.cards

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.postlogin.tab3_groups.GroupAccountState

@Composable
fun GroupAccountCard(
    groupAccount: GroupAccountState,
    onClick: () -> Unit
) {

    ClickableCard(
        onClick = onClick,
        mainContent = {
            Text(
                text = groupAccount.account.title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        bottomContent = {
            Text(
                text = "${stringResource(R.string.person_count)}: ${groupAccount.userCount}" ,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    )
}