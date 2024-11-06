package com.example.gorodbezproblem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorodbezproblem.modules.MainScreen
import com.example.gorodbezproblem.views.auth.LoginScreen
import com.example.gorodbezproblem.views.registration.PasswordScreen
import com.example.gorodbezproblem.views.registration.RegisterScreen
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("c60d9d3f-42be-4fa6-a050-8f39bb153dce")
        MapKitFactory.initialize(this)

        setContent {
            MaterialTheme {
                AppNavigation()  // Навигация приложения
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("password") {
            PasswordScreen(navController)
        }
        composable("main") {
            MainScreen() // Основной экран приложения
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        AppNavigation()
    }
}
