package com.example.gorodbezproblem.models

import com.google.gson.annotations.SerializedName

data class User(
    val username: String = "",
    val password: String = "",
    val phone: String = "",
    val birthday: String = "",
    val gender: String = "",
    val isAdmin: Boolean = false
)

data class AuthInfo(
    @SerializedName("Phone")
    val phone: String,

    @SerializedName("Password")
    val password: String,
)
