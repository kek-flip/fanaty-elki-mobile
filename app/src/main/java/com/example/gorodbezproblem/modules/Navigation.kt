package com.example.gorodbezproblem.modules

import MyMapView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import com.example.gorodbezproblem.views.problems.TasksScreen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Person
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.views.profile.ProfileScreen
import com.example.gorodbezproblem.views.problems.ProblemView
import com.example.gorodbezproblem.views.problems.CreateProblemView
import com.example.gorodbezproblem.views.location.LocationScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            if (currentRoute(navController) == NavigationItem.Home.route) {
                FloatingActionButton(
                    onClick = { navController.navigate("report_issue") },
                    shape = CircleShape,
                    containerColor = Colors.YellowGreen,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // Отступ для учёта навигационной панели
        ) {
            NavigationHost(
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Tasks,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = White // Белый фон для панели
    ) {
        val currentRoute = currentRoute(navController)
            ?: NavigationItem.Home.route // Подсвечиваем Home при запуске

        items.forEach { item ->
            // Проверяем текущий маршрут на экране
            val isHomeScreen = currentRoute == NavigationItem.Home.route ||
                    currentRoute == "location_screen" ||
                    currentRoute == "report_issue"
            val isTasksScreen = currentRoute == "task_details"

            BottomNavigationItem(
                icon = {
                    Box(
                        modifier = Modifier
                            .size(48.dp) // Размер контейнера
                            .background(
                                color = when {
                                    item.route == NavigationItem.Home.route && isHomeScreen -> Colors.YellowGreen
                                    item.route == NavigationItem.Tasks.route && isTasksScreen -> Colors.YellowGreen
                                    item.route == currentRoute -> Colors.YellowGreen
                                    else -> Color.Transparent // Подсветка для активной иконки
                                },
                                shape = RoundedCornerShape(12.dp) // Скругленные углы
                            ),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Icon(
                            item.icon,
                            contentDescription = item.title,
                            tint = Color.Black // Иконки всегда черного цвета
                        )
                    }
                },
                selected = (item.route == NavigationItem.Home.route && isHomeScreen) ||
                        (item.route == NavigationItem.Tasks.route && isTasksScreen) ||
                        item.route == currentRoute,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = false // Убираем текстовые подписи под иконками
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavigationItem.Home.route, modifier = modifier) {
        composable(NavigationItem.Home.route) { HomeScreen() }
        composable(NavigationItem.Tasks.route) { TasksScreen(navController) }
        composable(NavigationItem.Profile.route) { ProfileScreen() }
        composable("report_issue") { CreateProblemView(navController) }
        composable("task_details") { ProblemView(navController) }
        composable("location_screen") { LocationScreen(navController) }
    }
}


@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Карта отображается здесь
        MyMapView()
    }
}

@Composable
fun TasksScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Text(text = "Tasks Screen", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : NavigationItem("home", "Home", Icons.Outlined.LocationOn)
    object Tasks : NavigationItem("tasks", "Tasks", Icons.Outlined.CheckCircle)
    object Profile : NavigationItem("profile", "Profile", Icons.Outlined.Person)
}
