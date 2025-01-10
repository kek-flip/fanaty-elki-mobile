package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance
import okhttp3.MultipartBody

import java.io.File
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.models.AuthInfo
import com.example.gorodbezproblem.models.User
import com.example.gorodbezproblem.modules.getAuthToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody


import okhttp3.RequestBody.Companion.toRequestBody


class APIRepository {
    private val problemService = RetrofitInstance.getProblemService
    private val userService = RetrofitInstance.getUserService

    private val mediaBaseUrl = "http://83.166.237.142:8002/"

    suspend fun getProblems(token: String): List<Problem> {
        return problemService.getProblems(token).Body?.problems!!.map { problem ->
            val updatedMedia = problem.media.map { mediaId -> "$mediaBaseUrl$mediaId" }
            problem.copy(media = updatedMedia)
        }
    }

    suspend fun getProblem(problemId: Int, token: String): Problem {
        val apiResponse = problemService.getProblem(problemId, token)

        if (apiResponse.Body != null) {
            val problem = apiResponse.Body
            val updatedMedia = problem.media.map { mediaId -> "$mediaBaseUrl$mediaId.jpg" }
            return problem.copy(media = updatedMedia)
        } else {
            throw Exception("Ошибка при получении проблемы: ${apiResponse.Error?.toString() ?: "Неизвестная ошибка"}")
        }
    }

    // Создание проблемы с передачей заголовка, описания и изображений
    suspend fun createProblem(
        problem: Problem,
        mediaParts: List<MultipartBody.Part>,
        token: String,
    ) {
        val titleBody = problem.title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = problem.description.toRequestBody("text/plain".toMediaTypeOrNull())
        val specificLocationBody =
            problem.specificLocation.toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryBody = problem.category.toRequestBody("text/plain".toMediaTypeOrNull())
        val latBody = problem.lat.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val longBody = problem.long.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        problemService.createProblem(
            title = titleBody,
            description = descriptionBody,
            specificLocation = specificLocationBody,
            category = categoryBody,
            lat = latBody,
            long = longBody,
            mediaFiles = mediaParts,
            token,
        )
    }

    // Преобразование Uri в MultipartBody.Part
    fun createMultipartBodyPart(uri: Uri, context: Context): MultipartBody.Part {
        val filePath =
            getRealPathFromURI(context, uri) ?: throw IllegalArgumentException("Invalid URI")
        val file = File(filePath)
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("mediaFiles", file.name, requestBody)
    }

    // Получение реального пути из Uri
    private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        var result: String? = null
        val cursor: Cursor? = context.contentResolver.query(contentUri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            result = it.getString(columnIndex)
            it.close()
        }
        return result
    }

    suspend fun registerUser(user: User) {
        userService.createUser(user)
    }

    suspend fun auth(phone: String, password: String): String {
        val authInfo = AuthInfo(phone, password)

        val res = userService.auth(authInfo)

        val headers = res.headers()
        val authToken = headers["X-Auth-Token"] ?: return ""

        return authToken
    }
}

