package com.example.gorodbezproblem.views.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.views.auth.BaseAuthView

@Composable
fun RegistrationView(navController: NavHostController, viewModel: RegistrationViewModel = viewModel()) {
    var showError by remember { mutableStateOf(false) }

    BaseAuthView {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text("Укажите информацию о себе", style = MaterialTheme.typography.bodyLarge)

            // Поля ввода
            OutlinedTextField(
                value = viewModel.fullName,
                onValueChange = { viewModel.fullName = it },
                label = { Text("Имя и фамилия / Псевдоним") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.phoneNumber,
                onValueChange = { viewModel.phoneNumber = it },
                label = { Text("Номер телефона") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.birthDate,
                onValueChange = { viewModel.birthDate = it },
                label = { Text("Дата рождения") },
                modifier = Modifier.fillMaxWidth()
            )

            // Выбор пола
            Text("Пол", style = MaterialTheme.typography.bodyLarge)
            Row {
                RadioButton(
                    selected = viewModel.gender == "Мужской",
                    onClick = { viewModel.gender = "Мужской" }
                )
                Text("Мужской", modifier = Modifier.padding(start = 8.dp))

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = viewModel.gender == "Женский",
                    onClick = { viewModel.gender = "Женский" }
                )
                Text("Женский", modifier = Modifier.padding(start = 8.dp))
            }

            // Кнопка "Далее"
            Button(
                onClick = {
                    if (viewModel.fullName.isNotBlank() && viewModel.phoneNumber.isNotBlank() &&
                        viewModel.birthDate.isNotBlank() && viewModel.gender.isNotBlank()) {
                        // Все поля заполнены, можно переходить
                        navController.navigate(
                            "create_password/${viewModel.fullName}/${viewModel.phoneNumber}/${viewModel.birthDate}/${viewModel.gender}"
                        )
                    } else {
                        // Если хотя бы одно поле не заполнено, показываем ошибку
                        showError = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
            ) {
                Text("Далее", color = Colors.Black)
            }

            // Отображение ошибки
            if (showError) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    content = { Text("Пожалуйста, заполните все поля.") }
                )
            }
        }
    }
}

