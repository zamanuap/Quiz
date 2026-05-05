package com.example.quiz.presentation.ui.navigation

sealed class NavEvent {
    data object NavigateToHome : NavEvent()
    data object NavigateToStudy : NavEvent()
    data object NavigateToQuiz : NavEvent()
    data object NavigateToResult : NavEvent()
}