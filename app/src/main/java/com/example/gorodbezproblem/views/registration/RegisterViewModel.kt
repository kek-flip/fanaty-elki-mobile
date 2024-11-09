package com.example.gorodbezproblem.views.registration

import android.content.Context
import android.util.Log // Импортируем Log для отладки
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val context: Context) : ViewModel() {

    private val repository = APIRepository(context)

    var name by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var selectedGender by mutableStateOf("Мужской")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    // Добавим тег для логирования, чтобы использовать его во всех логах
    private val logTag = "RegisterViewModel"

    fun register() {
        Log.d(logTag, "Registering user with: name=$name, phoneNumber=$phoneNumber, birthDate=$birthDate, gender=$selectedGender")

        // Проверяем, что обязательные поля заполнены
        if (name.isNotBlank() && phoneNumber.isNotBlank() && birthDate.isNotBlank()) {
            viewModelScope.launch {
                isLoading = true
                Log.d(logTag, "Starting registration process...")

                val success = repository.register(name, phoneNumber, birthDate, selectedGender)

                isLoading = false
                if (success) {
                    Log.d(logTag, "Registration successful")
                    // Лог успешной регистрации
                } else {
                    isError = true
                    Log.e(logTag, "Registration failed")
                }
            }
        } else {
            Log.w(logTag, "Cannot register: some fields are blank")
        }
    }
}
