package com.example.gorodbezproblem.views.problems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProblemView(navController: NavHostController) {
    // Извлекаем taskId из предыдущего экрана
    val previousBackStackEntry = navController.previousBackStackEntry
    val taskId = previousBackStackEntry?.arguments?.getString("taskId") ?: "Неизвестная заявка"

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

        // Название проблемы
        Text(
            text = taskId,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Описание проблемы
        Text(
            text = "Описание проблемы",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Фото проблемы
        /*Image(
            painter = painterResource(id = R.drawable.example_image),
            contentDescription = "Problem Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )*/

        // Место
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

        // Статус
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Status",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "В работе", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

