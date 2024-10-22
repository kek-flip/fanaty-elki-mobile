package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance

class APIRepository {
    private val problemService = RetrofitInstance.getProblemService

    suspend fun getProblems(): List<Problem> {
        return problemService.getProblems()
    }
}