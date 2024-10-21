package com.example.gorodbezproblem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TasksScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок экрана
        Text(
            text = "Мои заявки",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Пример списка заявок (можно подключить реальные данные)
        val tasks = listOf(
            Task("Поломанный светофор", "Улица Пушкина, дом Колотушкина", "В работе"),
            Task("Неубранная улица", "Проспект Ленина, 23", "В работе")
        )

        // Список заявок
        tasks.forEach { task ->
            TaskItem(task, onClick = {
                // Сохраняем taskId в аргументах и переходим на экран деталей задачи
                navController.currentBackStackEntry?.arguments?.putString("taskId", task.title)
                navController.navigate("task_details")
            })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
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
                Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                Text(text = task.address, style = MaterialTheme.typography.bodySmall)
            }

            // Правая часть с иконкой времени и статусом
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Status",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = task.status, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

data class Task(val title: String, val address: String, val status: String)
