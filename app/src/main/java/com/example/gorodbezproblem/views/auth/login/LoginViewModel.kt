package com.example.gorodbezproblem.views.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.models.repository.APIRepository
import com.example.gorodbezproblem.modules.saveAuthToken
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = APIRepository()

    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")

    var isAuth by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun onLogin() {
        viewModelScope.launch {
            try {
                val token = repository.auth(phoneNumber, password)
                saveAuthToken(MainActivity.applicationContext(), token)
                isAuth = true
                isError = false
            } catch (e: Exception) {
                isAuth = false
                isError = true
            }
        }
    }
}