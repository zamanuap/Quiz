package com.example.quiz.presentation.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.presentation.ui.screens.HomeScreen
import com.example.quiz.presentation.ui.screens.QuizScreen
import com.example.quiz.presentation.ui.screens.ResultScreen
import com.example.quiz.presentation.ui.screens.StudyScreen
import com.example.quiz.presentation.viewmodel.QuizViewModel
import com.example.quiz.presentation.viewmodel.StudyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav() {
    val navController = rememberNavController()
    val studyViewModel: StudyViewModel = hiltViewModel()
    val quizViewModel: QuizViewModel = hiltViewModel()
    //val navigation = AppNavigation()
    val navigation = quizViewModel.getNavigationObject()

    LaunchedEffect(Unit) {
        //navigation.events.collect { event ->
        navigation.events.collect { event ->
            when(event) {
                is NavEvent.NavigateToHome -> {
                    navController.navigate("home")
                }
                is NavEvent.NavigateToQuiz -> {
                    navController.navigate("quiz")
                }
                is NavEvent.NavigateToStudy -> {
                    navController.navigate("study")
                }
                else -> {
                    navController.navigate("result")
                }
            }
        }
    }

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
                    studyViewModel
                    //onClickGoToQuizScreen = { navController.navigate("quiz")},
                    //onClickGoToStudyScreen = { navController.navigate("study")}
                )
            }
            composable("study") {
                StudyScreen(
                    studyViewModel
                    //onClickGoToHomeScreen = { navController.navigate("home")}
                )
            }

            composable("quiz") {
                QuizScreen(
                    quizViewModel
                    //onClickGoToResultScreen = { navController.navigate("result")}
                )
            }
            composable("result") {
                ResultScreen(
                    quizViewModel
                    //onClickGoToHomeScreen = { navController.navigate("home")}
                )
            }
        }
    }
}