package com.example.gorodbezproblem.views.problems.problemai

import android.content.Context
import android.webkit.JavascriptInterface
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.views.problems.createproblem.problem.CreateProblemViewModel

class WebAppInterface(
    private val context: Context,
    private val viewModel: CreateProblemViewModel,
    private val navController: NavHostController,
) {
    @JavascriptInterface
    fun onProblemTitleChange(title: String) {
        viewModel.onProblemTitleChange(title)
    }

    @JavascriptInterface
    fun onProblemDescriptionChange(desc: String) {
        viewModel.onProblemDescriptionChange(desc)
    }

    @JavascriptInterface
    fun onSpecificLocationChange(location: String) {
        viewModel.onSpecificLocationChange(location)
    }

    @JavascriptInterface
    fun createProblem() {
        viewModel.onSubmitClick(context)
        navController.navigate("home")
    }
}