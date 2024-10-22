package com.example.gorodbezproblem.models.api

import com.example.gorodbezproblem.models.Problem
import retrofit2.http.GET

interface ProblemService {
    @GET("/problems")
    suspend fun getProblems() : List<Problem>
}