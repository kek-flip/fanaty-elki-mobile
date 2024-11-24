package com.example.gorodbezproblem.models.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://83.166.237.142:8000"

    const val MEDIA_BASE_URL = "$BASE_URL/media/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getProblemService: ProblemService by lazy {
        retrofit.create(ProblemService::class.java)
    }
}
