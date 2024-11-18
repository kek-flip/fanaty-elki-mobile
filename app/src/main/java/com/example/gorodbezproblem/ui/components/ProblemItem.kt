package com.example.gorodbezproblem.ui.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun ProblemItem(problem: Problem, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .shadow(2.dp, RoundedCornerShape(UIConstants.Round))
        ,
        shape = RoundedCornerShape(UIConstants.Round),
        color = Colors.White
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Левая часть с названием и адресом
            Column() {
                Text(text = problem.title, fontSize = 20.sp)
                Text(text = problem.specificlocation, fontSize = 12.sp, fontStyle = FontStyle.Italic)
            }

            // Правая часть с иконкой времени и статусом
            ProblemStatus(problem.status)
        }
    }
}