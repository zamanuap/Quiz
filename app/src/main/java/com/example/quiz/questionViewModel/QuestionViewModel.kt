package com.example.quiz.questionViewModel

import androidx.lifecycle.ViewModel
import com.example.quiz.dataModel.Question
import com.example.quiz.dataModel.QuestionUiState
import com.example.quiz.dataModel.jsonData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class QuestionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(QuestionUiState())
    val uiState: StateFlow<QuestionUiState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }
    private fun loadQuestions() {
        try {
            val data = Json.decodeFromString<List<Question>>(jsonData)
            _uiState.update { it.copy(questions = data) }
        } catch (e: Exception) {
            _uiState.update { it.copy(errorMessage = e.message) }
        }
    }

    fun previousQuestion() {
        if(uiState.value.currentIndex > 0) {
            _uiState.update {
                it.copy(currentIndex = uiState.value.currentIndex - 1)
            }
        }
    }

    fun nextQuestion() {
        if(uiState.value.currentIndex < uiState.value.questions.lastIndex) {
            _uiState.update {
                it.copy(currentIndex = uiState.value.currentIndex + 1)
            }
        }
    }

    fun selectAnswer(qId: Int, optionIndex: Int) {
        _uiState.update {
            it.copy(selectedAnswers = uiState.value.selectedAnswers + (qId to optionIndex))
        }
    }

    fun score(): Int {
        return uiState.value.selectedAnswers.count {
            (qId, selectedAnswer) -> uiState.value.questions
            uiState.value.questions[qId - 1].correctAnswer == selectedAnswer
        }
    }
}