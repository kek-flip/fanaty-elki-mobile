package com.example.gorodbezproblem.views.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gorodbezproblem.models.repository.APIRepository

class RegistrationViewModel(private val repository: APIRepository = APIRepository()) : ViewModel() {

    var fullName by mutableStateOf("")

    var phoneNumber by mutableStateOf("")

    var birthDate by mutableStateOf("")

    var gender by mutableStateOf("")

}
