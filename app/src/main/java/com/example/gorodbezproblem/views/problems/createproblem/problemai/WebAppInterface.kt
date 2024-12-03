package com.example.gorodbezproblem.views.problems.problemai

import android.webkit.JavascriptInterface
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.views.problems.createproblem.problemai.ProblemAIViewModel

class WebAppInterface(
    private val viewModel: ProblemAIViewModel,
) {
    @JavascriptInterface
    fun createProblem(title: String, description: String, location: String) {
        viewModel.onProblemTitleChange(title)
        viewModel.onProblemDescriptionChange(description)
        viewModel.onSpecificLocationChange(location)
        viewModel.approve()
    }

    @JavascriptInterface
    fun discardProblem() {
        viewModel.discard()
    }
}