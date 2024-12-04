package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance
import okhttp3.MultipartBody

import java.io.File
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.gorodbezproblem.models.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody


import okhttp3.RequestBody.Companion.toRequestBody


class APIRepository {
    private val problemService = RetrofitInstance.getProblemService
    private val userService = RetrofitInstance.getUserService

    private val mediaBaseUrl = "http://83.166.237.142:8002/"

    suspend fun getProblems(): List<Problem> {
        return problemService.getProblems().Body?.problems!!.map { problem ->
            val updatedMedia = problem.media.map { mediaId -> "$mediaBaseUrl$mediaId" }
            problem.copy(media = updatedMedia)
        }
    }

    suspend fun getProblem(problemId: Int): Problem {
        val apiResponse = problemService.getProblem(problemId)

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
        mediaParts: List<MultipartBody.Part>
    ) {
        val titleBody = problem.title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = problem.description.toRequestBody("text/plain".toMediaTypeOrNull())
        val specificLocationBody = problem.specificLocation.toRequestBody("text/plain".toMediaTypeOrNull())
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
            mediaFiles = mediaParts
        )
    }

    // Преобразование Uri в MultipartBody.Part
    fun createMultipartBodyPart(uri: Uri, context: Context): MultipartBody.Part {
        val filePath = getRealPathFromURI(context, uri) ?: throw IllegalArgumentException("Invalid URI")
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
        val usernameBody = user.username.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordBody = user.password.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneBody = user.phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val birthdayBody = user.birthday.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderBody = user.gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val isAdminBody = user.isAdmin.toString().toRequestBody("text/plain".toMediaTypeOrNull()) // Преобразуем Boolean в строку

        val response = userService.createUser(
            username = usernameBody,
            password = passwordBody,
            phone = phoneBody,
            birthday = birthdayBody,
            gender = genderBody,
            isAdmin = isAdminBody
        )

        if (response.Error != null) {
            throw Exception("Ошибка при регистрации: ${response.Error}")
        }
    }

}

