package com.rma.expensetracker.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.useful.Record
import java.util.Locale

@Composable
fun RecordCard(
    record: Record,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            //Record date
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .weight(0.18f)
                    .fillMaxSize()
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                ) {
                    Text(
                        text = record.date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = stringResource(MonthLabel.entries[record.date.monthValue - 1].labelId),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Record content
            Column(modifier = Modifier.weight(0.82f).fillMaxWidth().padding(horizontal = 6.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(2.dp)
                ){
                    //TODO Provjeri treba li staviti ikonu (je li user isti kao trenutni)
                    Text(
                        text = "${record.user.firstName} ${record.user.lastName}",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "" //TODO dohvati ime usera
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(2.dp)
                ){
                    Text(
                        text = record.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if(record.amount > 0) {
                            "+${String.format(Locale.US, "%.2f", record.amount)}"
                        } else {
                            String.format(Locale.US, "%.2f", record.amount)
                        },
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                ) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 2.dp)) {
                            CategoryTag(
                                category = record.category
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class MonthLabel(val labelId: Int) {
    JANUARY(R.string.january_label),
    FEBRUARY(R.string.february_label),
    MARCH(R.string.march_label),
    APRIL(R.string.april_label),
    MAY(R.string.may_label),
    JUNE(R.string.june_label),
    JULY(R.string.july_label),
    AUGUST(R.string.august_label),
    SEPTEMBER(R.string.september_label),
    OCTOBER(R.string.october_label),
    NOVEMBER(R.string.november_label),
    DECEMBER(R.string.december_label)
}