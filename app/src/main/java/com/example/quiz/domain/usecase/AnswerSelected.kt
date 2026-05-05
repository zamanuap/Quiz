package com.example.quiz.domain.usecase

import javax.inject.Inject

class AnswerSelected @Inject constructor() {
    operator fun invoke(
        qid: Int,
        option: Int,
        quizSelectedAnswers: Map<Int, Int>
    ): Map<Int, Int> {
        return quizSelectedAnswers + (qid to option)
    }
}