package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.Problem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Запрос для аутентификации
data class AuthRequest(
    val Phone: String,
    val Password: String
)

// Запрос для регистрации
data class RegisterRequest(
    val username: String,
    val phone: String,
    val birthday: String,
    val gender: String,
    val isAdmin: Boolean = false
)

// Ответ на запрос аутентификации, содержит токен
data class AuthResponse(
    val token: String
)

// Общий шаблон ответа от API, включает данные или ошибку
data class APIResponse<T, E>(
    val Body: T? = null,
    val Error: E? = null
)

// Ответ с списком проблем
data class ProblemsListResponse(
    val problems: List<Problem>
)

// Модель пользователя
data class User(
    val id: Int,
    val username: String,
    val phone: String,
    val icon: String?,
    val birthday: String,
    val gender: String,
    val isAdmin: Boolean
)

// Интерфейс для взаимодействия с API
interface ProblemService {

    // Получение списка проблем
    @GET("/problems")
    suspend fun getProblems(): Response<APIResponse<ProblemsListResponse, Any>>

    // Получение одной проблемы по ID
    @GET("/problems/{id}")
    suspend fun getProblem(@Path("id") problemId: Int): Response<Problem>

    // Создание новой проблемы
    @POST("/problems")
    suspend fun createProblem(@Body problem: Problem): Response<Unit>

    // Логин и получение токена
    @POST("/session")
    suspend fun login(@Body authRequest: AuthRequest): Response<AuthResponse>

    // Регистрация пользователя
    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Unit>
    
    @POST("/users")
    suspend fun createUser(@Body registerRequest: RegisterRequest): Response<Unit>
}

