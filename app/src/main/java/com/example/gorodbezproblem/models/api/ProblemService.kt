package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.Problem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProblemService {
    @GET("/problems")
    suspend fun getProblems() : List<Problem>

    @POST("/problems")
    suspend fun createProblem(@Body problem: Problem)
}