package com.example.gorodbezproblem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gorodbezproblem.models.Problem

@Composable
fun ProblemItem(problem: Problem, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Левая часть с названием и адресом
            Column(modifier = Modifier.weight(1f)) {
                Text(text = problem.title, style = MaterialTheme.typography.bodyLarge)
                Text(text = problem.address, style = MaterialTheme.typography.bodySmall)
            }

            // Правая часть с иконкой времени и статусом
            ProblemStatus(problem.status)
        }
    }
}