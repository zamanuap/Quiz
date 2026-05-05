package com.example.quiz.domain.usecase

import com.example.quiz.domain.model.Question
import javax.inject.Inject

class ScoreUseCase @Inject constructor() {

    operator fun invoke(
        questions: List<Question>,
        answers: Map<Int, Int>
    ): Int {
        return questions.count { question ->
            answers[question.id] == question.correctAnswer
        }
    }
}