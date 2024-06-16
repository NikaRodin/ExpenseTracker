package com.rma.expensetracker.presentation.components.graphs

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.rma.expensetracker.data.models.useful.Record
import kotlin.math.absoluteValue

@Composable
fun PieGraph(
    recordsList: List<Record>
) {
    val valuesPerCategory: MutableMap<String, Pair<Double, Int>> = mutableMapOf()

    recordsList.forEach { record ->
        val key = record.category.title.lowercase()

        if(!valuesPerCategory.containsKey(key)) {
            valuesPerCategory[key] = Pair(
                record.amount.absoluteValue,
                record.category.color.toArgb()
            )
        } else {
            valuesPerCategory[key] = Pair(
                valuesPerCategory[key]!!.first +  record.amount.absoluteValue,
                valuesPerCategory[key]!!.second
            )
        }
    }

    var graphValues = valuesPerCategory.map { entry ->
        PieEntry(entry.value.first.toFloat(), entry.key)
    }
    var graphColors = valuesPerCategory.map { entry ->
        entry.value.second
    }

    if(graphValues.isEmpty()) {
        graphValues = listOf(PieEntry(1f, "Loading..."))
        graphColors = listOf(Color.LTGRAY)
    }

    val centerTextColor = MaterialTheme.colorScheme.onBackground.toArgb()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AndroidView(
            factory = { context ->
                PieChart(context).apply {
                    // Configure chart appearance
                    setUsePercentValues(true)
                    description.isEnabled = false
                    setExtraOffsets(0f, 5f, 0f, 0f)
                    dragDecelerationFrictionCoef = 0.95f
                    isDrawHoleEnabled = true
                    setHoleColor(Color.TRANSPARENT)
                    setTransparentCircleColor(Color.TRANSPARENT)
                    setTransparentCircleAlpha(110)
                    holeRadius = 40f
                    transparentCircleRadius = 0f
                    setDrawCenterText(true)
                    rotationAngle = 0f
                    isRotationEnabled = true
                    isHighlightPerTapEnabled = true
                    setEntryLabelColor(Color.BLACK)
                    setEntryLabelTextSize(10f)
                    legend.isEnabled = false
                    centerText = "%"
                    setCenterTextSize(20f)
                    setCenterTextColor(centerTextColor)
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { pieChart ->
                val dataSet = PieDataSet(graphValues, "")
                dataSet.colors = graphColors
                dataSet.sliceSpace = 3f
                dataSet.selectionShift = 5f
                val data = PieData(dataSet)
                data.setValueTextSize(10f)
                data.setValueTextColor(Color.BLACK)
                pieChart.data = data
                pieChart.invalidate()
            }
        )
    }
}