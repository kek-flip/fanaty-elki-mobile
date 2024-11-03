package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.Problem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class APIResponse<T, E>(
    val Body: T? = null,
    val Error: E? = null
)

data class ProblemsListResponse(
    val problems: List<Problem>
)

interface ProblemService {
    @GET("/problems")
    suspend fun getProblems() : APIResponse<ProblemsListResponse, Any>

    @GET("/problems/{id}")
    suspend fun getProblem(@Path("id") problemId: Int): Problem

    @POST("/problems")
    suspend fun createProblem(@Body problem: Problem)
}