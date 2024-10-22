package com.example.gorodbezproblem.views.problems.createproblem

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
    private var problem by mutableStateOf(Problem())
    var isCreated by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun onProblemTitleChange(title: String) {
        problem = Problem(title, problem.address, problem.status)
    }

    fun onProblemDescriptionChange(desc: String) {
        problem = Problem(problem.title, desc, problem.status)
    }

    fun onSubmitClick() {
        viewModelScope.launch {
            try {
                repository.createProblem(problem)
                isCreated = true
            } catch (_: Error) {
                isError = true
            }
        }
    }
}
