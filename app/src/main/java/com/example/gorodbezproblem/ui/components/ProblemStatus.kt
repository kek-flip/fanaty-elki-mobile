package com.example.gorodbezproblem.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Ожидание", style = MaterialTheme.typography.bodySmall)
            }

            Status.IN_PROGRESS -> {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Status",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "В работе", style = MaterialTheme.typography.bodySmall)
            }

            Status.DONE -> {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Status",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Проблема устранена", style = MaterialTheme.typography.bodySmall)
            }

            else -> TODO()
        }
    }
}