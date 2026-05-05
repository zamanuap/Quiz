package com.example.quiz.presentation.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.presentation.viewmodel.StudyViewModel

//import com.example.quiz.presentation.viewmodel.NavViewModel
//import com.example.quiz.presentation.viewmodel.QuestionViewModel


@Composable
fun HomeScreen(
    studyViewModel: StudyViewModel
    //onClickGoToQuizScreen: () -> Unit,
    //onClickGoToStudyScreen: () -> Unit
) {

    Row(modifier = Modifier) {
        Button(
            modifier = Modifier
                .height(60.dp)
                .padding(end = 4.dp)
                .weight(1f),
            onClick = { //onClickGoToQuizScreen()
                    studyViewModel.goToQuizScreen()
            }
        ) {
            Text("Quiz",
                style = TextStyle(fontSize = 22.sp)
            )
        }
        Button(
            modifier = Modifier
                .height(60.dp)
                .padding(start = 4.dp)
                .weight(1f),
            onClick = {
                //onClickGoToStudyScreen()
                studyViewModel.goToStudyScreen()
            }
        ) {
            Text("Study",
                style = TextStyle(fontSize = 22.sp)
            )
        }
    }
}