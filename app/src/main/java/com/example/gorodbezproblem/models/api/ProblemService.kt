package com.example.gorodbezproblem.models.api

import android.media.Image
import com.example.gorodbezproblem.models.Problem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    suspend fun getProblems(@Header("X-Auth-Token") token: String): APIResponse<ProblemsListResponse, Any>

    @GET("/problems/{id}")
    suspend fun getProblem(@Path("id") problemId: Int, @Header("X-Auth-Token") token: String): APIResponse<Problem, Any>

    @Multipart
    @POST("/problems")
    suspend fun createProblem(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("specificLocation") specificLocation: RequestBody,
        @Part("category") category: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("long") long: RequestBody,
        @Part mediaFiles: List<MultipartBody.Part>,
        @Header("X-Auth-Token") token: String
    )


}