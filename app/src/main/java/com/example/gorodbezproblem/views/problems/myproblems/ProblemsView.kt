package com.example.gorodbezproblem.views.problems.myproblems

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.ui.components.ProblemItem
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun TasksScreen(
    navController: NavHostController,
    viewModel: ProblemsViewModel = viewModel()
) {
    // Загружаем данные при запуске экрана
    LaunchedEffect(Unit) {
        viewModel.loadProblems()
    }

    // Состояния загрузки и ошибок
    val isLoaded = viewModel.isLoaded
    val isError = viewModel.isError

    Column(
        modifier = Modifier
            .padding(UIConstants.SidesPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // Заголовок экрана
        Text(
            text = "Мои заявки",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Проверка состояния загрузки
        if (!isLoaded) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        } else if (isError) {
            // Отображаем ошибку, если данные не загрузились
            Text(
                text = "Ошибка при загрузке данных.",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            // Список заявок, если данные загружены
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                viewModel.problems.forEach { task ->
                    ProblemItem(task, onClick = {
                        // Сохраняем taskId в аргументах и переходим на экран деталей задачи
                        navController.currentBackStackEntry?.arguments?.putString(
                            "taskId",
                            task.id.toString()
                        )
                        navController.navigate("task_details")
                    })
                }
            }
        }
    }
}
