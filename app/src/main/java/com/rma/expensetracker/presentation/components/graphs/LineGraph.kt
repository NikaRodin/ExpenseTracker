package com.rma.expensetracker.presentation.components.graphs

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.presentation.components.cards.MonthLabel
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties
import ir.ehsannarmani.compose_charts.models.StrokeStyle
import ir.ehsannarmani.compose_charts.models.ZeroLineProperties
import java.time.LocalDate

@Composable
fun LineGraph(
    recordsList: List<Record>
) {
    val recordsSortedByDate = recordsList.sortedWith(compareByDescending { it.date })
    val valuesPerDay: MutableMap<LocalDate, Double> = mutableMapOf()

    recordsSortedByDate.forEach { record ->
        if(!valuesPerDay.containsKey(record.date)) {
            valuesPerDay[record.date] = record.amount
        }else {
            valuesPerDay[record.date] = valuesPerDay[record.date]!! + record.amount
        }
    }

    var cumulativeSum = 0.0
    val cumulativeValuesPerDay: MutableMap<LocalDate, Double> = mutableMapOf()

    valuesPerDay.toSortedMap().forEach { (date, amount) ->
        cumulativeSum += amount
        cumulativeValuesPerDay[date] = cumulativeSum
    }

    val graphValues = cumulativeValuesPerDay.map {
        entry -> entry.value
    }.takeLast(7).toMutableList()

    val graphLabels = cumulativeValuesPerDay.map { entry ->
        "${entry.key.dayOfMonth} " + stringResource(
            MonthLabel.entries[entry.key.monthValue - 1].labelId
        )
    }.takeLast(7).toMutableList()

    if(graphValues.size <= 1) {
        graphValues.add(0, 0.0)
        graphLabels.add(0, "")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LineChart(
            modifier = Modifier.height(150.dp).fillMaxWidth().padding(12.dp),
            data = listOf(
                Line(
                    label = "",
                    values = graphValues.ifEmpty { listOf(0.0) },
                    color = SolidColor(MaterialTheme.colorScheme.primaryContainer),
                    firstGradientFillColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .5f),
                    secondGradientFillColor = Color.Transparent,
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(width = 2.dp),
                )
            ),
            animationMode = AnimationMode.Together(delayBuilder = {
                it * 500L
            }),
            labelProperties = LabelProperties(
                enabled = true,
                textStyle = MaterialTheme.typography.labelSmall,
                padding = 8.dp,
                labels = graphLabels.ifEmpty { listOf("") }
            ),
            labelHelperProperties = LabelHelperProperties(
                enabled = false
            ),
            gridProperties = GridProperties(
                enabled = true,
                xAxisProperties = GridProperties.AxisProperties(
                    enabled = true,
                    style = StrokeStyle.Normal,
                    thickness = (.5).dp,
                    lineCount = 5
                ),
                yAxisProperties = GridProperties.AxisProperties(
                    enabled = false,
                ),
            ),
            popupProperties = PopupProperties(
                enabled = false
            ),
            zeroLineProperties = ZeroLineProperties(
                enabled = true,
                color = SolidColor(Color.Red.copy(alpha = 0.5f)),
                thickness = 1.dp
            ),
        )
    }
}