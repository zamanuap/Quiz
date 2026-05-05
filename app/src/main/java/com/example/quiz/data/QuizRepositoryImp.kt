package com.example.quiz.data

import com.example.quiz.domain.model.Question
import com.example.quiz.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : QuizRepository {

    override suspend fun getQuestions(): List<Question> {
        return localDataSource.loadQuestions()
    }
}