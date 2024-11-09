package com.example.gorodbezproblem.views.problems.createproblem

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class CreateProblemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = APIRepository(application.applicationContext)

    private var problem by mutableStateOf(Problem())
    var isCreated by mutableStateOf(false)
    var isError by mutableStateOf(false)

    // Список для хранения URI фотографий
    val photoUris = mutableStateListOf<Uri>()

    fun onProblemTitleChange(title: String) {
        problem = Problem(
            title = title,
            description = problem.description,
            address = problem.address,
            status = problem.status,
            mediaFiles = problem.mediaFiles
        )
    }

    fun onProblemDescriptionChange(desc: String) {
        problem = Problem(
            title = problem.title,
            description = desc,
            address = problem.address,
            status = problem.status,
            mediaFiles = problem.mediaFiles
        )
    }

    // Метод для добавления URI фотографии
    fun addPhotoUri(uri: Uri) {
        if (photoUris.size < 3) {
            photoUris.add(uri)
        }
    }

    fun onSubmitClick() {
        viewModelScope.launch {
            try {
                // Отправка изображений на сервер
                val uploadResponse = repository.uploadImages(photoUris)
                if (uploadResponse.isSuccessful) {
                    isCreated = true
                    isError = false
                } else {
                    isError = true
                }
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}
