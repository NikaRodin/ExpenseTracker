package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.components.dialogs.PopUpDialog
import com.rma.expensetracker.presentation.postlogin.tab1_records.DataText
import com.rma.expensetracker.presentation.postlogin.tab1_records.SimpleDataRow
import java.util.Locale

@Composable
fun PreviewGroupInfo(
    groupAccount: Account,
    usersList: List<User>,
    viewModel: GroupDetailsViewModel,
    navController: NavHostController
) {
    val isLeaveGroupDialogOpen by viewModel.isLeaveGroupDialogOpen.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .padding(20.dp)
        ) {
            SimpleDataRow {
                DataText(text = stringResource(R.string.current_balance) + ":")
                DataText(text = if(groupAccount.balance > 0) {
                    "+${String.format(Locale.US, "%.2f", groupAccount.balance)}"
                } else {
                    String.format(Locale.US, "%.2f", groupAccount.balance)
                })
            }

            Spacer(modifier = Modifier.padding(4.dp))

            SimpleDataRow {
                DataText(text = stringResource(R.string.people) + " (${usersList.size})")
            }

            Divider(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                thickness = 2.dp
            )

            Spacer(modifier = Modifier.padding(2.dp))

            PopUpDialog(
                message = stringResource(R.string.are_you_sure_you_want_to_leave) ,
                dismissButtonText = stringResource(R.string.cancel),
                confirmButtonText = stringResource(R.string.confirm),
                isOpen = isLeaveGroupDialogOpen,
                onDismiss = viewModel::onLeaveGroupDismissed,
                onConfirm = { viewModel.onLeaveGroupConfirmed(navController) }
            )

            LazyColumn {
                items(usersList) { user ->
                    PersonRow(user) {
                        SuggestionChip(
                            onClick = { /*TODO slanje podsjetnika*/ },
                            label = { Text(stringResource(R.string.send_reminder)) },
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    if(usersList.size > 1)
                        viewModel.onLeaveGroupClicked()
                    else
                        viewModel.showWarningToast(
                            "Ne možete izaći iz grupe. U grupi mora ostati barem jedna osoba."
                        )
                },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_logout_24),
                        contentDescription = stringResource(R.string.leave_group),
                        tint = Color.Red
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.leave_group),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Red
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
fun PersonRow(
    user: User,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = stringResource(R.string.profile_picture)
            )
            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            Text(" ${user.firstName} ${user.lastName}")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if(trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}
