package com.example.quiz.presentation.state

import com.example.quiz.domain.model.Question

data class StudyUiState(
    val questions: List<Question> = emptyList(),
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    //val correctedAnswer: Set<Int> = emptySet(),
    val currentIndex: Int = 0,
    val errorMessage: String? = null,
)
