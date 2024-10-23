package com.example.gorodbezproblem.views.location

import MyMapView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn

@Composable
fun LocationScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        MyMapView()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Блок "Место" с иконкой и текстом
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Место",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Location", tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Улица Пушкина, дом Колотушкина", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.weight(1f)) // Для того, чтобы кнопка была внизу

        // Кнопка "Готово"
        Button(
            onClick = { navController.popBackStack() }, // Возвращаемся назад
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Готово", color = Color.White)
        }
    }
}
