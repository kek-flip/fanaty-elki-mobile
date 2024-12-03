package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserService {
    @Multipart
    @POST("/users")
    suspend fun createUser(
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("birthday") birthday: RequestBody,
        @Part("gender") gender: RequestBody
    ): APIResponse<Unit, Any>
}

