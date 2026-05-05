package com.example.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.domain.model.Question
import com.example.quiz.domain.usecase.AnswerSelected
import com.example.quiz.presentation.state.QuizUiState
import com.example.quiz.domain.usecase.GetAllQuestionsUseCase
import com.example.quiz.domain.usecase.GetQuizQuestionsUseCase
import com.example.quiz.domain.usecase.ScoreUseCase
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
class QuizViewModel @Inject constructor (
    private val getAllQuestions: GetAllQuestionsUseCase,
    private val getQuizQuestions: GetQuizQuestionsUseCase,
    private val scoreUseCase: ScoreUseCase,
    private val answerSelected: AnswerSelected,
    //private val navViewModel: NavViewModel
    private val navigation: AppNavigation
) : ViewModel() {

    private val _quizUiState = MutableStateFlow(QuizUiState())
    val quizUiState: StateFlow<QuizUiState> = _quizUiState.asStateFlow()
    var allQuestions: List<Question> = emptyList()

    init {
        loadQuiz()
    }

    fun getNavigationObject() : AppNavigation {
        return navigation
    }

    fun goToHomeScreen() {
        viewModelScope.launch {
            //navViewModel.navigate(NavEvent.NavigateToHome)
            navigation.navigate(NavEvent.NavigateToHome)
        }
    }

    fun goToResultScreen() {
        viewModelScope.launch {
            //navViewModel.navigate(NavEvent.NavigateToResult)
            navigation.navigate(NavEvent.NavigateToResult)
        }
    }

    private suspend fun loadAllQuestions() {
        allQuestions = getAllQuestions()
    }
    private fun loadQuiz() {
        viewModelScope.launch {
            //val allQuestions = getAllQuestions()
            loadAllQuestions()
            val quizQuestions =
                getQuizQuestions(allQuestions, minOf(allQuestions.size, 5))

            _quizUiState.update {
                it.copy(quizQuestions = quizQuestions)
            }
        }
    }

    fun reLoadQuiz() {
        viewModelScope.launch {
            val reLoadQuizQuestions =
                getQuizQuestions(allQuestions, minOf(allQuestions.size, 5))

            _quizUiState.update {
                it.copy(
                    quizQuestions = reLoadQuizQuestions,
                    quizSelectedAnswers = emptyMap(),
                    currentIndex = 0,
                    timeLeft = 0
                )
            }
        }
    }

    fun answerSelected(qId: Int, option: Int) {
        _quizUiState.update {
            it.copy(
                quizSelectedAnswers =
                    answerSelected(qId, option, quizUiState.value.quizSelectedAnswers)
            )
        }
    }

    fun previousQuestion() {
        if (quizUiState.value.currentIndex > 0) {
            _quizUiState.update {
                it.copy(currentIndex = quizUiState.value.currentIndex - 1)
            }
        }
    }

    fun nextQuestion() {
        if (quizUiState.value.currentIndex < quizUiState.value.quizQuestions.lastIndex) {
            _quizUiState.update {
                it.copy(currentIndex = quizUiState.value.currentIndex + 1)
            }
        }
    }
    fun score(): Int {
        return scoreUseCase(
            quizUiState.value.quizQuestions, quizUiState.value.quizSelectedAnswers
        )
    }
}