package com.example.quiz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(onClickGoToQuizScreen: () -> Unit) {
    Column {
        Button(
            onClick = { onClickGoToQuizScreen() }
        ) {
            Text("Start Quiz")
        }
    }
}