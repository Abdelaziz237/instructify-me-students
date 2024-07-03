package com.instructify_me.students.auth.presentation.sign_in.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent

class SignInViewModel: ViewModel() {
    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    fun onEvent(event: LoginPageEvent, callback: () -> Unit) {
        when (event) {
            is LoginPageEvent.Login -> {
                callback()
            }
            is LoginPageEvent.StartLoading -> {
                _state.value = true
            }
            is LoginPageEvent.StopLoading -> {
                _state.value = false
            }
        }
    }
}