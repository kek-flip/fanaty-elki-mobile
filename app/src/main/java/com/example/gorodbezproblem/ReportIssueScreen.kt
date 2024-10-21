package com.example.gorodbezproblem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ReportIssueScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Кнопка назад
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(8.dp)
                .size(24.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Заголовок
        Text(
            text = "Сообщить о проблеме",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Поле "Описание"
        Text(text = "Описание", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Введите краткое название проблемы") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле "Расскажите подробнее"
        Text(text = "Расскажите подробнее", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Введите подробности проблемы") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Блок "Фото"
        Text(text = "Фото", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray, shape = CircleShape)
                .clickable {
                    // Логика для выбора фото из галереи
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Photo", tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Блок "Место" с переходом на экран места
        Text(text = "Место", style = MaterialTheme.typography.bodyMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                navController.navigate("location_screen") // Переход на экран с местом
            }
        ) {
            Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Location", tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Ваш адрес", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Отправить"
        Button(
            onClick = { /* Логика отправки */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Отправить", color = Color.White)
        }
    }
}

