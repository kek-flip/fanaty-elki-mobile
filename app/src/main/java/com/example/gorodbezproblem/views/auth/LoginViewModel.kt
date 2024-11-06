package com.example.gorodbezproblem.views.auth

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {
    private val repository = APIRepository(context)

    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun login() {
        if (phoneNumber.isNotBlank() && password.isNotBlank()) {
            viewModelScope.launch {
                try {
                    isLoading = true
                    val success = repository.login(phoneNumber, password)
                    isLoading = false
                    if (success) {
                        // Вход успешен, можно переходить на главный экран
                    } else {
                        isError = true
                    }
                } catch (e: Exception) {
                    isError = true
                    isLoading = false
                }
            }
        }
    }
}

