package com.example.gorodbezproblem.models.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://83.166.237.142:8000"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getProblemService: ProblemService by lazy {
        retrofit.create(ProblemService::class.java)
    }
}