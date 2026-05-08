package com.example.quiz.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.example.quiz.presentation.viewmodel.StudyViewModel


@Composable
fun StudyScreen(
    studyViewModel: StudyViewModel
) {

    val uiState by studyViewModel.uiState.collectAsState()
    val question = uiState.questions[uiState.currentIndex]
    Column() {
        Text(
            question.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.padding(16.dp))

        question.options.forEachIndexed { optionIndex, option ->
            val isSelected = uiState.selectedAnswers[question.id] == optionIndex
            val isAnswered = uiState.selectedAnswers[question.id] != null
            AnswerOption(
                option = option,
                optionIndex = optionIndex,
                question = question,
                isSelected = isSelected,
                isAnswered = isAnswered,
                viewModel = studyViewModel
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            Button(
                modifier = Modifier.weight(1f).padding(end = 4.dp),
                onClick = { studyViewModel.previousQuestion() }
            ) {
                Text("Previous")
            }
            Button(
                modifier = Modifier.weight(1f).padding(start = 4.dp),
                onClick = {
                    if(uiState.currentIndex == uiState.questions.lastIndex) {
                        studyViewModel.goToHomeScreen()
                    }
                    studyViewModel.nextQuestion()
                }
            ) {
                Text(if(uiState.currentIndex == uiState.questions.lastIndex) "Finish" else "Next")
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
    viewModel: StudyViewModel
) {

    val isCorrect = optionIndex == question.correctAnswer

    val bgColor = when {
        isSelected && isCorrect -> Color(0xFF4CAF50)   // ✅ Green
        isSelected && !isCorrect -> Color(0xFFF44336) // ❌ Red
        isAnswered && isCorrect -> Color(0xFF4CAF50)
        //isSelected -> Color(0xFFBBDEFB) // 🔵 Selected (before submit)
        else -> Color.LightGray
    }


    Row(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                onClick = {
                    viewModel.selectedAnswer(question.id, optionIndex)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                viewModel.selectedAnswer(question.id,optionIndex)
            }
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

