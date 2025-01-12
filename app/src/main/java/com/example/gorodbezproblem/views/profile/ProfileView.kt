package com.example.gorodbezproblem.views.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.modules.deleteAuthToken
import com.example.gorodbezproblem.ui.theme.Colors

@Composable
fun ProfileScreen(navController: NavHostController) {
    // Создаем состояния для полей ввода
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Круглая иконка с зеленой рамкой
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(2.dp, Colors.YellowGreen, CircleShape)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Person, contentDescription = "Profile Icon", tint = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Заголовок "Мои данные"
        Text(
            text = "Мои данные",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода для имени
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода для номера телефона
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Номер телефона") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода для даты рождения с иконкой календаря
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Дата рождения") },
            trailingIcon = {
                Icon(Icons.Outlined.DateRange, contentDescription = "Calendar Icon")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Выйти" с красным шрифтом
        TextButton(
            onClick = {
                deleteAuthToken(MainActivity.applicationContext())
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Выйти",
                color = Red,
                fontSize = 18.sp
            )
        }
    }
}
