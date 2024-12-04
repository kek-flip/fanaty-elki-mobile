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
    viewModel: CreatePasswordViewModel = viewModel()
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
            var errorMessage by remember { mutableStateOf("") }

            Button(
                onClick = {
                    if (viewModel.password.isBlank() || viewModel.confirmPassword.isBlank()) {
                        errorMessage = "Пароли не могут быть пустыми. Имя: $fullName, Телефон: $phoneNumber"
                        showError = true
                    } else if (viewModel.password != viewModel.confirmPassword) {
                        errorMessage = "Пароли не совпадают. Имя: $fullName, Телефон: $phoneNumber"
                        showError = true
                    } else {
                        viewModel.validateAndRegisterUser(
                            fullName = fullName,
                            phoneNumber = phoneNumber,
                            birthDate = birthDate,
                            gender = gender,
                            password = viewModel.password,
                            isAdmin = false,
                            onSuccess = { navController.navigate("login") },
                            onError = { error ->
                                errorMessage = "Ошибка регистрации: $error. Данные: Имя - $fullName, Пароль - ${viewModel.password}"
                                showError = true
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
            ) {
                Text("Зарегистрироваться", color = Colors.Black)
            }

            if (showError) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    content = { Text(errorMessage) }
                )
            }

        }
    }
}

