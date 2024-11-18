package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance


class APIRepository {
    private val problemService = RetrofitInstance.getProblemService

    suspend fun getProblems(): List<Problem> {
        return problemService.getProblems().Body?.problems!!
    }

    suspend fun getProblem(problemId: Int): Problem {
        return problemService.getProblem(problemId)
    }

    suspend fun createProblem(problem: Problem) {
        return problemService.createProblem(
            problem.title,
            problem.description,
            problem.specificlocation,
            problem.category,
            problem.lat,
            problem.long,
            listOf(),
        )
    }
}