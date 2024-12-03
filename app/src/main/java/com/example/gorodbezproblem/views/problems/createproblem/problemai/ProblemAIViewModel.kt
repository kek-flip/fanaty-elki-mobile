package com.example.gorodbezproblem.views.problems.createproblem.problemai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gorodbezproblem.models.Problem

class ProblemAIViewModel : ViewModel() {
    var approved by mutableStateOf(false)
    var discard by mutableStateOf(false)

    var problem by mutableStateOf(Problem()) // Объект Problem

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

    fun approve() {
        approved = true
    }

    fun discard() {
        discard = true
    }
}