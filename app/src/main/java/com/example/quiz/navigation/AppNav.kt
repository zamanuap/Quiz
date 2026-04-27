package com.example.quiz.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.questionViewModel.QuestionViewModel
import com.example.quiz.screens.HomeScreen
import com.example.quiz.screens.QuizScreen
import com.example.quiz.screens.ResultScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav() {
    val navController = rememberNavController()
    val viewModel : QuestionViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("CitizenShip Test") })
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)

        ) {
            composable("home") {
                HomeScreen(
                    onClickGoToQuizScreen = { navController.navigate("quiz")}
                )
            }
            composable("quiz") {
                QuizScreen(
                    viewModel,
                    onClickGoToResultScreen = { navController.navigate("result")}
                )
            }
            composable("result") {
                ResultScreen(
                    viewModel
                )
            }
        }
    }

}