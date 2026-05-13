package com.example.quiz.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.presentation.viewmodel.QuizViewModel


@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    //onClickGoToResultScreen: () -> Unit
) {
    val uiState by viewModel.quizUiState.collectAsState()
    val question = uiState.quizQuestions[uiState.currentIndex]
    val minutes = uiState.timeLeft / 60
    val seconds = uiState.timeLeft % 60

    LaunchedEffect(Unit) {
        viewModel.timerStart()
    }

    BackButtonPress(
        onExit = {
            viewModel.goToHomeScreen()
            viewModel.timerStop()
        }
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if(seconds < 10) {
                BlinkingText(minutes, seconds)
            } else {
                Text(
                    text = String.format("Time left- %02d:%02d", minutes, seconds),
                    color = Color.Blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = question.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.padding(12.dp))

        question.options.forEachIndexed { optionIndex, option ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = uiState.quizSelectedAnswers[question.id] == optionIndex,
                        onClick = {
                            viewModel.answerSelected(question.id, optionIndex)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                RadioButton(
                    selected =  uiState.quizSelectedAnswers[question.id] == optionIndex,
                    onClick = {
                        viewModel.answerSelected(question.id, optionIndex)
                    }
                )

                Text(
                    text = option,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
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

@Composable
fun BlinkingText(minutes: Int, seconds: Int) {

    val infiniteTransition = rememberInfiniteTransition()

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )
    Text(
        text = "Time left- ",
        color = Color.Red,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.alpha(alpha)
    )

    Text(
        text = String.format("%02d:%02d", minutes, seconds),
        color = Color.Red,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun BackButtonPress( onExit: () -> Unit) {
    var showDialog by remember { mutableStateOf(false)}

    BackHandler {
        showDialog = true
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Exiting Quiz")
            },

            text = {
                Text("Do you want to leave?")
            },

            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onExit()
                    }
                ) {
                    Text("Yes")
                }
            },

            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}


