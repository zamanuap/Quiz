package com.example.quiz.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.domain.model.Question
import com.example.quiz.presentation.viewmodel.QuizViewModel

@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    //onClickGoToHomeScreen : () -> Unit
    ) {
        val uiState by viewModel.quizUiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Test Completed !!!")
            Text(
                "Your score: ${viewModel.score()}/5",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                //onClickGoToHomeScreen()
                viewModel.reLoadQuiz()
                viewModel.goToHomeScreen()
            }) {
                Text("Go to Home")
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                "Review your answers",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

        //uiState.quizQuestions.forEachIndexed { index, question ->
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.quizQuestions) { question ->

                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        question.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    question.options.forEachIndexed { optionIndex, option ->
                        val isSelected = uiState.quizSelectedAnswers[question.id] == optionIndex
                        val isAnswered = uiState.quizSelectedAnswers[question.id] != null
                        AnswerOption(
                            option = option,
                            optionIndex = optionIndex,
                            question = question,
                            isSelected = isSelected,
                            isAnswered = isAnswered,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }

@Composable
fun AnswerOption(
    option: String,
    optionIndex: Int,
    question: Question,
    isSelected: Boolean,
    isAnswered: Boolean,
    viewModel: QuizViewModel
) {
    val isCorrect = optionIndex == question.correctAnswer

    val bgColor = when {
        isSelected && isCorrect -> Color(0xFF4CAF50)   // ✅ Green
        isSelected && !isCorrect -> Color(0xFFF44336) // ❌ Red
        !isAnswered && isCorrect -> Color(0xFF4CAF50)
        isAnswered && isCorrect -> Color(0xFF4CAF50)
        //isSelected -> Color(0xFFBBDEFB) // 🔵 Selected (before submit)
        else -> Color.LightGray
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = { }
        )
        Text(
            text = option,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
                .background(bgColor, RoundedCornerShape(8.dp))
                .padding(8.dp)
                .weight(1f)
        )
    }
}