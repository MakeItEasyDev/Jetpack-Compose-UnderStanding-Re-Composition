package com.jetpack.recomposition

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReComposeViewModel: ViewModel() {
    private val _state = MutableStateFlow(ReComposeState())
    val state: StateFlow<ReComposeState> get() = _state.asStateFlow()

    fun updateCounter() {
        _state.value = state.value.copy(counter = state.value.counter.plus(1))
    }

    fun updateSlider(value: Int) {
        _state.value = state.value.copy(sliderValue = value)
    }
}