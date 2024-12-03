package com.example.gorodbezproblem.views.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
}