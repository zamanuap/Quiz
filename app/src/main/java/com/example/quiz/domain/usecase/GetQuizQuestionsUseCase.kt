package com.example.quiz.domain.usecase

import com.example.quiz.domain.model.Question
import javax.inject.Inject

class GetQuizQuestionsUseCase @Inject constructor(
) {
    operator fun invoke(
        questions: List<Question>,
        count: Int
    ): List<Question> {
        return questions.shuffled().take(count)
    }
}