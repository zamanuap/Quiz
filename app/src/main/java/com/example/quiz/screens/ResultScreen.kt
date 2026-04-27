package com.example.quiz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.quiz.questionViewModel.QuestionViewModel

@Composable
fun ResultScreen(viewModel: QuestionViewModel) {
    Column() {
        Text("Test Completed !!!")
        Text("Your score: ${viewModel.score()}/5")
    }
}