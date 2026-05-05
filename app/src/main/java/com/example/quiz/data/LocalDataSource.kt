package com.example.quiz.data

import com.example.quiz.domain.model.Question
import jakarta.inject.Inject
import kotlinx.serialization.json.Json

class LocalDataSource @Inject constructor() {
    fun loadQuestions(): List<Question> {
        val questionList = Json.decodeFromString<List<Question>>(jsonData)
        return questionList
    }
}