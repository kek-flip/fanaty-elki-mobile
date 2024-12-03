package com.example.gorodbezproblem.models

data class User(
    val username: String = "",
    val password: String = "",
    val phone: String = "",
    val birthday: String = "",
    val gender: String = "",
    val isAdmin: Boolean = false
)
