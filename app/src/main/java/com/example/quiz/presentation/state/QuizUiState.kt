package com.example.quiz.presentation.state

import com.example.quiz.domain.model.Question

data class QuizUiState (
    val quizQuestions: List<Question> = emptyList(),
    val quizSelectedAnswers: Map<Int, Int> = emptyMap(),
    val currentIndex: Int = 0,
    val timeLeft: Int = 20,
)