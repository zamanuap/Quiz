package com.example.quiz.domain.repository

import com.example.quiz.domain.model.Question

interface QuizRepository {
        suspend fun getQuestions(): List<Question>
    }