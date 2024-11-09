package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File
import java.io.InputStream



class APIRepository(private val context: Context) {
    private val problemService = RetrofitInstance.getProblemService

    suspend fun getProblems(): List<Problem> {
        return problemService.getProblems().Body?.problems!!
    }

    suspend fun getProblem(problemId: Int): Problem {
        return problemService.getProblem(problemId)
    }

    suspend fun createProblem(problem: Problem) {
        return problemService.createProblem(problem)
    }

    // Функция для подготовки MultipartBody.Part из URI.
    private fun getMultipartFromUri(uri: Uri, paramName: String): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir).apply {
            outputStream().use { output ->
                inputStream?.copyTo(output)
            }
        }

        val requestBody = tempFile.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(paramName, tempFile.name, requestBody)
    }

    // Метод для загрузки изображений
    suspend fun uploadImages(uris: List<Uri>): Response<Unit> {
        val imageParts = uris.map { getMultipartFromUri(it, "images[]") }
        return problemService.uploadImages(imageParts)
    }
}