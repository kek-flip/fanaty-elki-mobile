package com.example.gorodbezproblem.views.problems.createproblem.problem

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.repository.APIRepository
import com.example.gorodbezproblem.modules.getAuthToken
import kotlinx.coroutines.launch


class CreateProblemViewModel : ViewModel() {
    private val repository = APIRepository()

    var problem by mutableStateOf(Problem()) // Объект Problem
    var selectedImages: List<Uri> by mutableStateOf(emptyList()) // Список выбранных изображений
    var isCreated by mutableStateOf(false) // Статус создания
    var isError by mutableStateOf(false) // Статус ошибки
    var inNotAuth by mutableStateOf(false)

    // Обработчики изменения данных
    fun onProblemTitleChange(newTitle: String) {
        problem = problem.copy(title = newTitle)
    }

    fun onProblemDescriptionChange(newDescription: String) {
        problem = problem.copy(description = newDescription)
    }

    fun onSpecificLocationChange(newLocation: String) {
        problem = problem.copy(specificLocation = newLocation)
    }

    // Обработка выбора изображений
    fun onImagesSelected(uris: List<Uri>) {
        selectedImages = uris
    }

    // Отправка данных на сервер
    fun onSubmitClick(context: Context) {
        viewModelScope.launch {
            try {
                val token = getAuthToken(MainActivity.applicationContext())

                if (token == null) {
                    inNotAuth = true
                } else {

                    val mediaParts = selectedImages.map { uri ->
                        repository.createMultipartBodyPart(uri, context)
                    }

                    repository.createProblem(
                        problem = problem,
                        mediaParts = mediaParts,
                        token
                    )
                    isCreated = true
                }
            } catch (e: Exception) {
                isError = true
                e.printStackTrace()
            }
        }
    }
}
