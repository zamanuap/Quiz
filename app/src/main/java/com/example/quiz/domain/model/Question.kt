package com.example.quiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int
)