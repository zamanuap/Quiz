package com.example.quiz.domain.usecase

import com.example.quiz.domain.model.Question
import com.example.quiz.domain.repository.QuizRepository
import jakarta.inject.Inject

class GetAllQuestionsUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.getQuestions()
    }
}