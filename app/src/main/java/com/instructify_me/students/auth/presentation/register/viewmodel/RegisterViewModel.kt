package com.instructify_me.students.auth.presentation.register.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.instructify_me.students.auth.presentation.register.info.RegisterPageEvent

class RegisterViewModel: ViewModel() {
    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    fun onEvent(event: RegisterPageEvent, callback: () -> Unit) {
        callback()
    }
}