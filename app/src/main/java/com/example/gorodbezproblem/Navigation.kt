package com.example.gorodbezproblem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationHost(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Settings
    )
    BottomNavigation() {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavigationItem.Home.route, modifier = modifier) {
        composable(NavigationItem.Home.route) { HomeScreen() }
        composable(NavigationItem.Settings.route) { SettingsScreen() }
    }
}

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Text(text = "Home Screen", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun SettingsScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Text(text = "Settings Screen", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

sealed class NavigationItem(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : NavigationItem("home", "Home", Icons.Filled.Home)
    object Settings : NavigationItem("settings", "Settings", Icons.Filled.Settings)
}
