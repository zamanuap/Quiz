package com.example.quiz.di

import com.example.quiz.data.QuizRepositoryImpl
import com.example.quiz.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindQuizRepository(
        impl: QuizRepositoryImpl
    ): QuizRepository
}