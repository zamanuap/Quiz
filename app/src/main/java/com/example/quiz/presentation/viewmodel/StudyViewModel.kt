package com.example.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.presentation.state.StudyUiState
import com.example.quiz.domain.usecase.AnswerSelected
import com.example.quiz.domain.usecase.GetAllQuestionsUseCase
import com.example.quiz.presentation.ui.navigation.AppNavigation
import com.example.quiz.presentation.ui.navigation.NavEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val getAllQuestions: GetAllQuestionsUseCase,
    private val answerSelected: AnswerSelected,
    private val appNavigation: AppNavigation
): ViewModel() {
    private val _uiState = MutableStateFlow(StudyUiState())
    val uiState: StateFlow<StudyUiState> = _uiState.asStateFlow()

    fun goToHomeScreen() {
        viewModelScope.launch {
            appNavigation.navigate(NavEvent.NavigateToHome)
        }
    }
    fun goToStudyScreen() {
        viewModelScope.launch {
            appNavigation.navigate(NavEvent.NavigateToStudy)
        }
    }

    fun goToQuizScreen() {
        viewModelScope.launch {
            appNavigation.navigate(NavEvent.NavigateToQuiz)
        }
    }

    init {
        loadQuestions()
    }
    private fun loadQuestions() {
        viewModelScope.launch {
            val allQuestions = getAllQuestions()

            _uiState.update {
                it.copy(questions = allQuestions)
            }
        }
    }

    fun previousQuestion() {
        if (uiState.value.currentIndex > 0) {
            _uiState.update {
                it.copy(currentIndex = uiState.value.currentIndex - 1)
            }
        }
    }

    fun nextQuestion() {
        if (uiState.value.currentIndex < uiState.value.questions.lastIndex) {
            _uiState.update {
                it.copy(currentIndex = uiState.value.currentIndex + 1)
            }
        }
    }

    fun selectedAnswer(qId: Int, optionIndex: Int) {
        _uiState.update {
            it.copy(
                selectedAnswers =
                    answerSelected(
                        qId, optionIndex, uiState.value.selectedAnswers
                    )
            )
        }
    }
}
