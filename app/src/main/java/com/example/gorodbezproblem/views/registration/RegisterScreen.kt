package com.example.gorodbezproblem.views.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gorodbezproblem.R

@Composable
fun RegisterScreen(navController: NavController) {
    // Получаем контекст
    val context = LocalContext.current

    // Создаем ViewModel вручную, передавая контекст
    val registerViewModel = remember { RegisterViewModel(context) }
    val isLoading = registerViewModel.isLoading
    val isError = registerViewModel.isError

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(top = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Urban", style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black))
                Text("Care", style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50)))
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = registerViewModel.name,
                onValueChange = { registerViewModel.name = it },
                label = { Text("Имя, фамилия или псевдоним") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = registerViewModel.phoneNumber,
                onValueChange = { registerViewModel.phoneNumber = it },
                label = { Text("Номер телефона") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = registerViewModel.birthDate,
                onValueChange = { registerViewModel.birthDate = it },
                label = { Text("Дата рождения") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = registerViewModel.selectedGender == "Мужской",
                        onClick = { registerViewModel.selectedGender = "Мужской" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF4CAF50))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Мужской")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = registerViewModel.selectedGender == "Женский",
                        onClick = { registerViewModel.selectedGender = "Женский" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF4CAF50))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Женский")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка регистрации с учетом загрузки
            Button(
                onClick = {
                        navController.navigate("password")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !isLoading // Блокируем кнопку, если идет процесс регистрации
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Далее", color = Color.White, fontSize = 16.sp)
                }
            }

            // Отображение ошибки, если таковая есть
            if (isError) {
                Text(
                    text = "Ошибка регистрации. Попробуйте снова.",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.reg_auth_city),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}


