package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.presentation.components.CategoryTag
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PreviewRecordInfo(record: Record, account: Account?) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        SimpleDataRow {
            DataText(text = record.title)
            DataText(text = if(record.amount >= 0) "+${record.amount}" else "${record.amount}")
        }
        SimpleDataRow {
            DataText(text = stringResource(R.string.date) + ":")
            DataText(text = record.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        }
        SimpleDataRow {
            DataText(text = stringResource(R.string.account) + ":")
            if (account != null)
                DataText(text = account.title)
            else
                DataText(text = stringResource(R.string.error))
        }
        SimpleDataColumn {
            DataText(text = stringResource(R.string.categories) + ":")
            FlowRow(modifier = Modifier.padding(8.dp)) {
                CategoryTag(
                    text = record.category.title,
                    color = record.category.color
                )
            }

        }
        SimpleDataColumn {
            DataText(text = stringResource(R.string.notes) + ":")
            Text(
                text = record.notes?: stringResource(R.string.no_notes),
                textAlign = TextAlign.Justify
            )
        }
        SimpleDataColumn {
            DataText(text = stringResource(R.string.photos) + ":")
            if(record.photos.isNotEmpty()) {
                //TODO Display Photos
            } else {
                Text(text = stringResource(R.string.no_photos))
            }
        }
    }
}

@Composable
fun SimpleDataRow(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}

@Composable
fun SimpleDataColumn(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        content()
    }
}

@Composable
fun DataText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}