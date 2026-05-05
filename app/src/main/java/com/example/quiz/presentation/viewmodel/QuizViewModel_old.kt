package com.example.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quiz.data.jsonData
import com.example.quiz.domain.model.Question
import com.example.quiz.presentation.state.QuizUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class QuizViewModel_old: ViewModel() {
    private val _quizUiState = MutableStateFlow(QuizUiState())
    val quizUiState: StateFlow<QuizUiState> = _quizUiState.asStateFlow()
    var questions: List<Question> = emptyList()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        questions = Json.decodeFromString<List<Question>>(jsonData)
    }

    fun loadQuizQuestions() {
        val quizQuestions = questions.shuffled().take(5)
        _quizUiState.update {
            it.copy(
                quizQuestions = quizQuestions
            )
        }
    }
}