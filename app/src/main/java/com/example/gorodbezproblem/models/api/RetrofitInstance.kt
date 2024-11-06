package com.example.gorodbezproblem.models.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://83.166.237.142:8000"
    private var authToken: String? = null

    // Метод для установки токена
    fun setAuthToken(token: String?) {
        authToken = token
    }

    // Интерцептор для добавления заголовка с токеном
    private fun getAuthInterceptor() = Interceptor { chain ->
        val originalRequest: Request = chain.request()

        // Пропускаем добавление токена для эндпоинтов авторизации и регистрации
        val request = if (authToken != null && !originalRequest.url().toString().contains("/session") && !originalRequest.url().toString().contains("/register")) {
            originalRequest.newBuilder()
                .addHeader("X-Auth-Token", authToken!!)
                .build()
        } else {
            originalRequest
        }

        chain.proceed(request)
    }

    // Настройка OkHttpClient с интерцептором
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor())
            .build()
    }

    // Создание Retrofit с учетом OkHttpClient
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getProblemService: ProblemService by lazy {
        retrofit.create(ProblemService::class.java)
    }
}
