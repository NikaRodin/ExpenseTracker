package com.rma.expensetracker.presentation.components.other

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun ColorPicker(
    displayPreview: Boolean = true,
    colorState: Color,
    onColorChange: (Color) -> Unit
) {
    var selectedColor by remember { mutableStateOf(colorState) }
    var darkness by remember { mutableFloatStateOf(colorState.alpha) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if(displayPreview) {
            // Display selected color with adjusted darkness
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .size(50.dp)
                    .background(selectedColor.copy(alpha = darkness))
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Color Picker
        LinearColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) { color ->
            selectedColor = color
            onColorChange(selectedColor.copy(alpha = darkness))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Darkness Picker
        LinearDarknessPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            color = selectedColor
        ) { newDarkness ->
            darkness = newDarkness
            onColorChange(selectedColor.copy(alpha = darkness))
        }
    }
}

@Composable
fun LinearColorPicker(
    modifier: Modifier = Modifier,
    onColorSelected: (Color) -> Unit
) {
    var pickerWidth by remember { mutableFloatStateOf(0f) }
    var selectedX by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    selectedX = offset.x
                    onColorSelected(getColorFromPosition(offset.x, pickerWidth))
                }
            }
            .background(Color.Gray)
            .onSizeChanged {
                pickerWidth = it.width.toFloat()
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    isAntiAlias = true
                }
                for (x in 0 until size.width.toInt()) {
                    paint.color = getColorFromPosition(x.toFloat(), size.width)
                    canvas.drawLine(
                        Offset(x.toFloat(), 0f),
                        Offset(x.toFloat(), size.height),
                        paint
                    )
                }
            }
        }

        // Draw indicator
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Black,
                radius = 20f,
                center = Offset(selectedX, size.height / 2)
            )
        }
    }
}

@Composable
fun LinearDarknessPicker(
    modifier: Modifier = Modifier,
    color: Color,
    onDarknessChanged: (Float) -> Unit
) {
    var pickerWidth by remember { mutableFloatStateOf(0f) }
    var selectedX by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    selectedX = offset.x
                    onDarknessChanged(getDarknessFromPosition(offset.x, pickerWidth))
                }
            }
            .background(Color.Gray)
            .onSizeChanged {
                pickerWidth = it.width.toFloat()
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    isAntiAlias = true
                }
                for (x in 0 until size.width.toInt()) {
                    paint.color = color.copy(alpha = getDarknessFromPosition(x.toFloat(), size.width))
                    canvas.drawLine(
                        Offset(x.toFloat(), 0f),
                        Offset(x.toFloat(), size.height),
                        paint
                    )
                }
            }
        }

        // Draw indicator
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Black,
                radius = 20f,
                center = Offset(selectedX, size.height / 2)
            )
        }
    }
}

fun getColorFromPosition(x: Float, width: Float): Color {
    val hue = (x / width) * 360
    return Color.hsv(hue, 1f, 1f)
}

fun getDarknessFromPosition(x: Float, width: Float): Float {
    return max(0f, min(1f, x / width))
}
