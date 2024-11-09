package com.example.gorodbezproblem.views.map

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.models.repository.APIRepository
import kotlinx.coroutines.launch

class MapViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = APIRepository(application.applicationContext)

    var problems: List<Problem> by mutableStateOf(arrayListOf())
    var isLoaded by  mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun loadProblems() {
        viewModelScope.launch {
            try {
                problems = repository.getProblems()
                isLoaded = true
                isError = false
            } catch (_: Error) {
                isError = true
            }
        }
    }
}