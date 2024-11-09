package com.example.gorodbezproblem.views.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gorodbezproblem.R

@Composable
fun PasswordScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current
    val registerViewModel = remember { RegisterViewModel(context) }
    val isLoading = registerViewModel.isLoading
    val isError = registerViewModel.isError

    // Следим за изменениями isError и isLoading для навигации после успешной регистрации
    LaunchedEffect(isError, isLoading) {
        if (!isError && !isLoading) {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

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

            Text("Придумайте пароль", style = TextStyle(fontSize = 16.sp))

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Повторите пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    registerViewModel.register()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Зарегистрироваться", color = Color.White, fontSize = 16.sp)
                }
            }

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
