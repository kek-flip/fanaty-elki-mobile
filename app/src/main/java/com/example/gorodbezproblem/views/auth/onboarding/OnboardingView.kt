package com.example.gorodbezproblem.views.auth.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.R
import com.example.gorodbezproblem.modules.NavigationItem
import com.example.gorodbezproblem.ui.components.ComposableCarousal
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.ui.theme.UIConstants
import com.example.gorodbezproblem.views.auth.BaseAuthView

@Composable
fun OnboardingView(navController: NavHostController, viewModel: OnboardingViewModel = viewModel()) {
    BaseAuthView {
        Column (
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ComposableCarousal(
                carouselItems = listOf(
                    R.drawable.start,
                    R.drawable.problem,
                    R.drawable.status,
                ),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Button(
                    onClick = { navController.navigate(NavigationItem.Home.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(UIConstants.Round),
                    colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
                ) {
                    Text("Войти", color = Colors.Black)
                }

                Button(
                    onClick = { navController.navigate(NavigationItem.Home.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .shadow(4.dp, RoundedCornerShape(UIConstants.Round)),
                    shape = RoundedCornerShape(UIConstants.Round),
                    colors = ButtonDefaults.buttonColors(containerColor = Colors.White)
                ) {
                    Text("Еще нет аккаунта?", color = Colors.Black)
                }
            }
        }

    }
}
