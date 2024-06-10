package com.rma.expensetracker.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.data.models.mock.Category

@Composable
fun CategoryTag(
    category: Category,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    isSelected: Boolean = false,
    onClick: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(80.dp),
        border = BorderStroke(
            width = if (isSelected) 4.dp else 1.dp,
            color = category.color
        ),
        color = if(isSelected) category.color.copy(alpha = 0.4f)  else Color.Transparent,
        modifier = Modifier
            .clickable(enabled = onClick != null) {
                if (onClick != null) {
                    onClick(category.id)
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(6.dp).padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            if(trailingIcon != null) {
                Spacer(modifier = Modifier.padding(2.dp))
            }

            Text(
                text = category.title,
                color = category.color,
                textAlign = TextAlign.Center,
                style = textStyle,
                fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
            )

            if(trailingIcon != null) {
                Spacer(modifier = Modifier.padding(2.dp))
                trailingIcon()
            }
        }

    }
}