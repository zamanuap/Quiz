package com.example.quiz.presentation.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiz.presentation.viewmodel.QuizViewModel


@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    //onClickGoToResultScreen: () -> Unit
) {
    val uiState by viewModel.quizUiState.collectAsState()
    val question = uiState.quizQuestions[uiState.currentIndex]

    Column {
        Text(question.text)

        Spacer(modifier = Modifier.padding(12.dp))

        question.options.forEachIndexed { optionIndex, option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = uiState.quizSelectedAnswers[question.id] == optionIndex,
                    onClick = {
                        viewModel.answerSelected(question.id, optionIndex)
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
                    if(uiState.currentIndex == uiState.quizQuestions.lastIndex) {
                        //onClickGoToResultScreen()
                        viewModel.reLoadQuiz()
                        viewModel.goToResultScreen()
                    }
                    viewModel.nextQuestion()
                }
            ) {
                Text(if(uiState.currentIndex == uiState.quizQuestions.lastIndex) "Finish" else "Next")
            }
        }
    }
}


