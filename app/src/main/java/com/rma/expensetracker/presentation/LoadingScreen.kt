package com.rma.expensetracker.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {

    val backgroundColor = animateColorAsState(targetValue = Color.Transparent, label = "animateColorAsState")
    val widthModifier = Modifier.fillMaxWidth()

    AlertDialog(
        modifier = modifier
            .animateContentSize()
            .then(widthModifier),
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false ,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        buttons = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        title = null,
        text = null,
        backgroundColor = backgroundColor.value
    )
}
