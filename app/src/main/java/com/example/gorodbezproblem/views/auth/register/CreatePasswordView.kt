package com.example.gorodbezproblem.views.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.views.auth.BaseAuthView
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreatePasswordView(
    navController: NavHostController,
    fullName: String,
    phoneNumber: String,
    birthDate: String,
    gender: String,
    viewModel: CreatePasswordViewModel = viewModel() // Используем viewModel() правильно
) {
    BaseAuthView {
        var showError by remember { mutableStateOf(false) }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text("Создайте пароль", style = MaterialTheme.typography.bodyLarge)

            // Поле для ввода пароля
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Пароль") },
                modifier = Modifier.fillMaxWidth()
            )

            // Поле для ввода подтверждения пароля
            OutlinedTextField(
                value = viewModel.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = { Text("Подтвердите пароль") },
                modifier = Modifier.fillMaxWidth()
            )

            // Кнопка для завершения регистрации
            Button(
                onClick = {
                    // Проверка на совпадение паролей
                    if (viewModel.password.isNotBlank() && viewModel.confirmPassword.isNotBlank() && viewModel.password == viewModel.confirmPassword) {
                        viewModel.validateAndRegisterUser(
                            fullName = fullName,
                            phoneNumber = phoneNumber,
                            birthDate = birthDate,
                            gender = gender,
                            onSuccess = { navController.navigate("login") },  // Переход на экран входа
                            onError = { showError = true }  // Показать ошибку
                        )
                    } else {
                        showError = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
            ) {
                Text("Зарегистрироваться", color = Colors.Black)
            }

            // Ошибка ввода
            if (showError) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    content = { Text("Пожалуйста, заполните все поля корректно.") }
                )
            }
        }
    }
}
