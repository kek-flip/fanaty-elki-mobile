package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.AuthInfo
import com.example.gorodbezproblem.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST

interface UserService {
    @POST("/users")
    suspend fun createUser(@Body user: User): Response<Unit>

    @POST("/session")
    suspend fun auth(@Body authInfo: AuthInfo): Response<Unit>
}


