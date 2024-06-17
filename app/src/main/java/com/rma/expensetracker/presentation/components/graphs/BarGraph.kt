package com.rma.expensetracker.presentation.components.graphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.data.models.useful.Record
import kotlin.math.absoluteValue

@Composable
fun BarGraph(
    recordsList: List<Record>
) {
    val valuesPerPerson: MutableMap<User, Pair<Double, Double>> = mutableMapOf()

    recordsList.forEach { record ->
        val key = record.user

        if(!valuesPerPerson.containsKey(key)) {
            valuesPerPerson[key] = Pair(
                if(record.amount < 0) record.amount.absoluteValue else 0.0,
                if(record.amount > 0) record.amount else 0.0,
            )
        } else {
            valuesPerPerson[key] = Pair(
                if(record.amount < 0) {
                    valuesPerPerson[key]!!.first + record.amount.absoluteValue
                } else valuesPerPerson[key]!!.first,

                if(record.amount > 0) {
                    valuesPerPerson[key]!!.second + record.amount
                } else valuesPerPerson[key]!!.second,
            )
        }
    }

    var index = 0f
    var expenses = valuesPerPerson.map { entry -> BarEntry(index++, entry.value.first.toFloat()) }
    index = 0f
    var incomes = valuesPerPerson.map { entry -> BarEntry(index++, entry.value.second.toFloat()) }

    var people = valuesPerPerson.map { entry -> "${entry.key.firstName} ${entry.key.lastName}" }

    if(expenses.isEmpty()) {
        expenses = listOf(BarEntry(0f, 0f))
        incomes = listOf(BarEntry(0f, 0f))
        people = listOf("Loading...")
    }
    
    val textColor = MaterialTheme.colorScheme.onBackground.toArgb()
    val expensesLabel = stringResource(R.string.expenses_by_person)
    val incomesLabel = stringResource(R.string.incomes_by_person)
    val expensesColor = MaterialTheme.colorScheme.error.toArgb()
    val incomesColor = MaterialTheme.colorScheme.primary.toArgb()

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setDrawGridBackground(false)
                setFitBars(true)

                // Configure the X Axis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.setDrawAxisLine(true)
                xAxis.textColor = textColor // Set x-axis text color to white
                xAxis.textSize = 12f // Set x-axis text color to white
                xAxis.valueFormatter = CustomValueFormatter(people)
                xAxis.granularity = 1f
                xAxis.labelCount = 5
                xAxis.setCenterAxisLabels(true)

                // Configure the Y Axis
                axisLeft.setDrawGridLines(false)
                axisLeft.setDrawAxisLine(false)
                axisLeft.textColor = textColor // Set y-axis text color to white
                axisRight.isEnabled = false

                // Configure the Legend
                legend.isEnabled = true
                legend.textColor = textColor // Set legend text color to white
                legend.textSize = 14f // Set legend text size to 14sp
                legend.xEntrySpace = 20f
                legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                legend.orientation = Legend.LegendOrientation.HORIZONTAL
                legend.setDrawInside(false)
                legend.yOffset = 20f
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        update = { barChart ->
            val set1 = BarDataSet(expenses, expensesLabel).apply {
                color = expensesColor
                valueTextColor = textColor // Set bar value text color to white
                valueTextSize = 12f
            }
            val set2 = BarDataSet(incomes, incomesLabel).apply {
                color = incomesColor
                valueTextColor = textColor // Set bar value text color to white
                valueTextSize = 12f
            }

            val data = BarData(set1, set2)
            data.setValueTextColor(textColor) // Set value text color to white
            barChart.data = data

            // Configure the bar chart for horizontal bars
            barChart.axisLeft.setDrawLabels(false)
            barChart.axisRight.setDrawLabels(false)
            barChart.setDrawValueAboveBar(true)
            barChart.setFitBars(true)
            barChart.invalidate() // Refresh the chart

            // Set bar width and group bars
            val groupSpace = 0.4f
            val barSpace = 0.03f
            val barWidth = 0.3f
            data.barWidth = barWidth

            // Group the bars
            barChart.xAxis.axisMinimum = 0f
            barChart.xAxis.axisMaximum = expenses.size.toFloat()
            data.groupBars(0f, groupSpace, barSpace)

            barChart.invalidate() // Refresh the chart with grouping
        }
    )
}

class CustomValueFormatter(private val labels: List<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return if (value.toInt() in labels.indices) {
            labels[value.toInt()]
        } else {
            value.toString()
        }
    }
}