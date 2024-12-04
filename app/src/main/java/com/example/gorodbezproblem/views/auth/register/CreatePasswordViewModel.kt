package com.example.gorodbezproblem.views.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.User
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class CreatePasswordViewModel(private val repository: APIRepository = APIRepository()) : ViewModel() {

    // Поля для хранения пароля и его подтверждения
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var registrationSuccess by mutableStateOf(false)
    var registrationError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")


    fun validateAndRegisterUser(
        fullName: String,
        phoneNumber: String,
        birthDate: String,
        gender: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        if (password == confirmPassword) {

            val user = User(
                username = fullName,
                phone = phoneNumber,
                birthday = birthDate,
                gender = gender,
                password = password
            )


            viewModelScope.launch {
                try {

                    repository.registerUser(user)
                    registrationSuccess = true
                    onSuccess() // Успех
                } catch (e: Exception) {
                    registrationError = true
                    errorMessage = e.message ?: "Неизвестная ошибка"
                    onError(errorMessage)
                }
            }
        } else {
            // Если пароли не совпадают, выводим ошибку
            registrationError = true
            errorMessage = "Пароли не совпадают"
            onError(errorMessage)
        }
    }

    // Обработчики изменения значений паролей
    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
    }
}
