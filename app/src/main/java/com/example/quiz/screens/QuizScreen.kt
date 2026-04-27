package com.example.quiz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiz.questionViewModel.QuestionViewModel

@Composable
fun QuizScreen(
    viewModel: QuestionViewModel,
    onClickGoToResultScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val question = uiState.questions[uiState.currentIndex]

    Column {
        Text(question.text)

        Spacer(modifier = Modifier.padding(12.dp))

        question.options.forEachIndexed { optionIndex, option ->
            Row {
                RadioButton(
                    selected = uiState.selectedAnswers[question.id] == optionIndex,
                    onClick = {
                        viewModel.selectAnswer(question.id, optionIndex)
                    }
                )

                Text(text = option)
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            Button(
                modifier = Modifier.weight(1f).padding(end = 4.dp),
                onClick = { viewModel.previousQuestion() }
            ) {
                Text("Previous")
            }
            Button(
                modifier = Modifier.weight(1f).padding(start = 4.dp),
                onClick = {
                    if(uiState.currentIndex == uiState.questions.lastIndex) {
                        onClickGoToResultScreen()
                    }
                    viewModel.nextQuestion()
                }
            ) {
                Text(if(uiState.currentIndex == uiState.questions.lastIndex) "Finish" else "Next")
            }
        }
    }
}
