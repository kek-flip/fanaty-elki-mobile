package com.example.gorodbezproblem.views.problems.problemai

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.gorodbezproblem.views.problems.createproblem.problem.CreateProblemViewModel
import com.example.gorodbezproblem.views.problems.createproblem.problemai.ProblemAIViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ProblemAIView(
    navController: NavHostController,
    viewModel: ProblemAIViewModel = viewModel()
) {
    LaunchedEffect(viewModel.approved) {
        if (viewModel.approved) {
            navController.navigate("report_issue/${viewModel.problem.title}/${viewModel.problem.description}/${viewModel.problem.specificLocation}")
        }
    }

    LaunchedEffect(viewModel.discard) {
        if (viewModel.discard) {
            navController.navigate("report_issue///")
        }
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // Открывает ссылки в этом WebView
                settings.javaScriptEnabled = true
                addJavascriptInterface(WebAppInterface(viewModel), "AndroidInterface")
                loadUrl("http://83.166.237.142:8001/")
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}