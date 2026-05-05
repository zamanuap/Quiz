package com.example.quiz.presentation.ui.navigation

import jakarta.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@Singleton
class AppNavigation @Inject constructor() {
    private val _events = MutableSharedFlow<NavEvent>()
    val events: SharedFlow<NavEvent> = _events.asSharedFlow()

    suspend fun navigate(event: NavEvent) {
        _events.emit(event)
    }
}