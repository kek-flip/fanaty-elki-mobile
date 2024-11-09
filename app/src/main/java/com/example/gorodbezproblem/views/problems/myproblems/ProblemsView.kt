package com.example.gorodbezproblem.views.problems.myproblems

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    LaunchedEffect(Unit) {
        viewModel.loadProblems()
    }

    val scrollState = rememberScrollState()

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

        // Список заявок
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
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
