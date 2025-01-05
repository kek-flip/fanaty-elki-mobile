package com.example.gorodbezproblem.views.problems.myproblems

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

class ProblemsViewModel : ViewModel() {
    private val repository = APIRepository()

    var problems: List<Problem> by mutableStateOf(arrayListOf())
    var isLoaded by  mutableStateOf(false)
    var isError by mutableStateOf(false)
    var inNotAuth by mutableStateOf(false)

    fun loadProblems() {
        viewModelScope.launch {
            try {
                val token = getAuthToken(MainActivity.applicationContext())

                if (token == null) {
                    inNotAuth = true
                } else {
                    problems = repository.getProblems(token)
                    isLoaded = true
                    isError = false
                }
            } catch (_: Error) {
                isError = true
            }
        }
    }
}