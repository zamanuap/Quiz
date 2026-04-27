package com.example.quiz.dataModel

data class QuestionUiState(
    val questions: List<Question> = emptyList(),
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    //val correctedAnswer: Set<Int> = emptySet(),
    val currentIndex: Int = 0,
    val timeLeft: Int = 20,
    val errorMessage: String? = null
)
