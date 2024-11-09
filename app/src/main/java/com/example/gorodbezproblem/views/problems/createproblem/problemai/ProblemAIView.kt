package com.example.gorodbezproblem.views.problems.problemai

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.gorodbezproblem.views.problems.createproblem.CreateProblemViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ProblemAIView(
    navController: NavHostController,
    viewModel: CreateProblemViewModel = viewModel()
){
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // Открывает ссылки в этом WebView
                settings.javaScriptEnabled = true
                addJavascriptInterface(WebAppInterface(context, viewModel, navController), "AndroidInterface")
                loadUrl("http://83.166.237.142:8001/")
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}