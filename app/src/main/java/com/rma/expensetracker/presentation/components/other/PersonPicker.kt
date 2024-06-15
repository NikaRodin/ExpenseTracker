package com.rma.expensetracker.presentation.components.other

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PersonPicker(
    selectedUsers: List<User>,
    searchQueryState: InputFieldState,
    onUserSelected: (User) -> Unit,
    onUserDeselected: (User) -> Unit,
    getFilteredUsers: () -> List<User>
) {

    Column {
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            selectedUsers.forEach { user ->
                PersonChip(user = user, onDismiss = { onUserDeselected(it) })
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQueryState.text,
            onValueChange = searchQueryState.onTextChange,
            label = { Text(stringResource(id = R.string.search_people)) },
            placeholder = { Text(stringResource(id = R.string.search_people_placeholder)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search_people_icon_description)
                )
            }
        )

        val filteredUsers = getFilteredUsers()
        LazyColumn(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10)
                )
                .clip(RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(filteredUsers) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onUserSelected(user) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.padding(15.dp),
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
                }
            }
        }
    }
}

@Composable
fun PersonChip(user: User, onDismiss: (User) -> Unit) {
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(60.dp)
            .clickable { onDismiss(user) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(top = 10.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = stringResource(R.string.profile_picture)
            )
            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            Text(
                text = " ${user.firstName}",
                style = MaterialTheme.typography.labelSmall,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = stringResource(R.string.dimiss_button),
                tint = Color.Red,
                modifier = Modifier
                    .padding(0.dp)
                    .scale(0.7f)
            )
        }
    }
}