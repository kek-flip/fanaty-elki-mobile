package com.example.gorodbezproblem.views.problems.createproblem.problem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class CreateProblemViewModel : ViewModel() {
    private val repository = APIRepository()
    var problem by mutableStateOf(Problem())
    var isCreated by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun onProblemTitleChange(title: String) {
        problem = Problem(
            title = title,
            specificlocation = problem.specificlocation,
            status = problem.status
        )
    }

    fun onProblemDescriptionChange(desc: String) {
        problem = Problem(
            title = problem.title,
            description = desc,
            status = problem.status
        )
    }

    fun onSubmitClick() {
        viewModelScope.launch {
            try {
                repository.createProblem(problem)
                isCreated = true
                isError = false
            } catch (_: Error) {
                isError = true
            }
        }
    }
}
