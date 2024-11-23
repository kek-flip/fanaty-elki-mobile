package com.example.gorodbezproblem.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.ui.theme.UIConstants

class Status {
    companion object {
        val AWAITING = "AWAITING"
        val IN_PROGRESS = "IN_PROGRESS"
        val DONE = "DONE"
    }
}

@Composable
fun ProblemStatus(status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (status) {
            Status.AWAITING -> {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Status",
                    tint = Colors.Yellow
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Ожидание", fontSize = UIConstants.FontSize)
            }

            Status.IN_PROGRESS -> {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Status",
                    tint = Colors.Blue
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "В работе", fontSize = UIConstants.FontSize)
            }

            Status.DONE -> {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "Status",
                    tint = Colors.YellowGreen
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Проблема устранена", fontSize = UIConstants.FontSize)
            }
        }
    }
}