package com.example.gorodbezproblem.views.problems

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.repository.APIRepository
import com.example.gorodbezproblem.modules.getAuthToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProblemViewModel(private val problemId: Int) : ViewModel() {
    private val repository = APIRepository()

    private val _problemState = MutableStateFlow<Problem?>(null) // Состояние проблемы
    val problemState: StateFlow<Problem?> = _problemState

    private val _isLoading = MutableStateFlow(true) // Состояние загрузки
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null) // Ошибка
    val errorMessage: StateFlow<String?> = _errorMessage

    var inNotAuth by mutableStateOf(false)

    fun loadProblem() {
        viewModelScope.launch {
            try {
                val token = getAuthToken(MainActivity.applicationContext())

                if (token == null) {
                    inNotAuth = true
                } else {
                    _isLoading.value = true
                    val problem = repository.getProblem(problemId, token)
                    _problemState.value = problem
                    _errorMessage.value = null // Очистить ошибки, если загрузка успешна
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}


class ProblemViewModelFactory(private val problemId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProblemViewModel::class.java)) {
            return ProblemViewModel(problemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
