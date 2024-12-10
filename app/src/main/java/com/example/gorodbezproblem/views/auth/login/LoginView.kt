package com.example.gorodbezproblem.views.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.modules.NavigationItem
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.views.auth.BaseAuthView

@Composable
fun LoginView(navController: NavHostController, viewModel: LoginViewModel = viewModel()) {
    LaunchedEffect(viewModel.isAuth) {
        if (viewModel.isAuth) {
            navController.navigate("home")
        }
    }

    BaseAuthView {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Поля ввода
            OutlinedTextField(
                value = viewModel.phoneNumber,
                onValueChange = { viewModel.phoneNumber = it },
                label = { Text("Номер телефона") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            // Кнопки
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Button(
                    onClick = { viewModel.onLogin() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
                ) {
                    Text("Войти", color = Colors.Black)
                }

                Button(
                    onClick = { navController.navigate("registration") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Colors.White)
                ) {
                    Text("Еще нет аккаунта?", color = Colors.Black)
                }
            }
        }
    }
}



