package com.example.gorodbezproblem.models.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.AuthRequest
import com.example.gorodbezproblem.models.api.RegisterRequest
import com.example.gorodbezproblem.models.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class APIRepository(context: Context) {

    private val problemService = RetrofitInstance.getProblemService
    private var authToken: String? = null

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    init {
        // Загружаем токен из SharedPreferences при инициализации
        authToken = sharedPreferences.getString("AUTH_TOKEN", null)
        RetrofitInstance.setAuthToken(authToken)  // Устанавливаем токен в RetrofitInstance
    }

    suspend fun getProblems(): List<Problem> {
        val response = problemService.getProblems()
        if (response.isSuccessful) {
            return response.body()?.Body?.problems ?: emptyList()
        } else {
            throw Exception("Failed to fetch problems: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getProblem(problemId: Int): Problem {
        val response = problemService.getProblem(problemId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Problem not found")
        } else {
            throw Exception("Failed to fetch problem: ${response.errorBody()?.string()}")
        }
    }

    suspend fun createProblem(problem: Problem) {
        val response = problemService.createProblem(problem)
        if (!response.isSuccessful) {
            throw Exception("Failed to create problem: ${response.errorBody()?.string()}")
        }
    }

    suspend fun login(phoneNumber: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = problemService.login(AuthRequest(Phone = phoneNumber, Password = password))
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token != null) {
                    authToken = token
                    saveAuthToken(token)  // Сохраняем токен в SharedPreferences
                    RetrofitInstance.setAuthToken(token)  // Устанавливаем токен в RetrofitInstance
                    return@withContext true
                } else {
                    throw Exception("Auth token missing in response")
                }
            } else {
                throw Exception("Login failed: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun register(name: String, phoneNumber: String, birthDate: String, gender: String): Boolean {
        return withContext(Dispatchers.IO) {
            val regreg = RegisterRequest(name, phoneNumber, birthDate, gender)
            System.err.println("ERROR$regreg")
            val response = problemService.createUser(regreg)
            if (response.isSuccessful) {
                return@withContext true
            } else {
                throw Exception("Registration failed: ${response.errorBody()?.string()}")
            }
        }
    }

    private fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString("AUTH_TOKEN", token).apply()
    }

    fun getAuthToken(): String? {
        return authToken
    }

    fun clearAuthToken() {
        authToken = null
        sharedPreferences.edit().remove("AUTH_TOKEN").apply()
        RetrofitInstance.setAuthToken(null)  // Удаляем токен из RetrofitInstance
    }
}
