package com.example.quiz.presentation.viewmodel

import com.example.quiz.presentation.ui.navigation.NavEvent
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


/*
class NavViewModel @Inject constructor(): ViewModel() {
    private val _events = MutableSharedFlow<NavEvent>()
    val events: SharedFlow<NavEvent> = _events.asSharedFlow()

    suspend fun navigate(event: NavEvent) {
        _events.emit(event)
    }
}
 */
class Navigation_1 @Inject constructor() {
    private val _events = MutableSharedFlow<NavEvent>()
    val events: SharedFlow<NavEvent> = _events.asSharedFlow()

    suspend fun navigate(event: NavEvent) {
        _events.emit(event)
    }
}