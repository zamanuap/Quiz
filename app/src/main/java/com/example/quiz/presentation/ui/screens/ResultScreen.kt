package com.example.quiz.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiz.presentation.viewmodel.QuizViewModel

@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    //onClickGoToHomeScreen : () -> Unit
    ) {
    Column() {
        Text("Test Completed !!!")
        Text("Your score: ${viewModel.score()}/5")
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            //onClickGoToHomeScreen()
            viewModel.reLoadQuiz()
            viewModel.goToHomeScreen()
        }) {
            Text("Go to Home")
        }
    }
}