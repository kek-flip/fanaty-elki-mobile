package com.example.gorodbezproblem.views.location

import MyMapView
import androidx.compose.foundation.background
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
import com.example.gorodbezproblem.ui.theme.Colors

@Composable
fun LocationScreen(navController: NavHostController) {
    // Карта будет на заднем плане
    Box(modifier = Modifier.fillMaxSize()) {
        MyMapView(navController) // Здесь отображается карта

        // Блок с текстом и кнопкой внизу экрана
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Выравнивание по нижней части экрана
                .background(Color.White) // Белый фон для блока
                .padding(16.dp)
        ) {
            // Блок "Место" с иконкой и текстом
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Улица Пушкина, дом Колотушкина", style = MaterialTheme.typography.bodyLarge)
            }

            // Кнопка "Готово"
            Button(
                onClick = { navController.popBackStack() }, // Возвращаемся назад
                colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Готово", color = Color.White)
            }
        }
    }
}
