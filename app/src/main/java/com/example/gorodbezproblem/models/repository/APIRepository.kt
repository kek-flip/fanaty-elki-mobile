package com.example.gorodbezproblem.models.repository

import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.api.RetrofitInstance
import okhttp3.MultipartBody

import okhttp3.RequestBody
import java.io.File
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody


class APIRepository {
    private val problemService = RetrofitInstance.getProblemService

    suspend fun getProblems(): List<Problem> {
        return problemService.getProblems().Body?.problems!!
    }

    suspend fun getProblem(problemId: Int): Problem {
        return problemService.getProblem(problemId)
    }

    // Создание проблемы с передачей заголовка, описания и изображений
    suspend fun createProblem(
        problem: Problem, // Принимаем объект Problem
        mediaParts: List<MultipartBody.Part>
    ) {
        return problemService.createProblem(
            title = problem.title,
            description = problem.description,
            specificLocation = problem.specificlocation,
            category = problem.category,
            lat = problem.lat,
            long = problem.long,
            mediaFiles = mediaParts // Отправляем MultipartBody.Part
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
}

