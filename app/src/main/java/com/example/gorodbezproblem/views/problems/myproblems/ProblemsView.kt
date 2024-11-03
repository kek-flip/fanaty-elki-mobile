package com.example.gorodbezproblem.views.problems.myproblems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.ui.components.ProblemItem

@Composable
fun TasksScreen(
    navController: NavHostController,
    viewModel: ProblemsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadProblems()
    }

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

        // Пример списка заявок (можно подключить реальные данные

        // Список заявок
        viewModel.problems.forEach { task ->
            ProblemItem(task, onClick = {
                // Сохраняем taskId в аргументах и переходим на экран деталей задачи
                navController.currentBackStackEntry?.arguments?.putString("taskId", task.id.toString())
                navController.navigate("task_details")
            })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
