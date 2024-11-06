package com.example.gorodbezproblem.views.registration

import android.content.Context
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

    fun register() {
        if (name.isNotBlank() && phoneNumber.isNotBlank() && birthDate.isNotBlank()) {
            viewModelScope.launch {
                    isLoading = true
                    val success = repository.register(name, phoneNumber, birthDate, selectedGender)
                    isLoading = false
                    if (success) {
                        // Регистрация успешна, можно переходить на экран пароля
                    } else {
                        isError = true
                    }
            }
        }
    }
}
