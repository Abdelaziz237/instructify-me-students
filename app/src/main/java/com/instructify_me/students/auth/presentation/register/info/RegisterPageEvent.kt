package com.instructify_me.students.auth.presentation.register.info

sealed class RegisterPageEvent {
    data class Register(val username: String, val email: String, val password: String): RegisterPageEvent()
    object StartLoading: RegisterPageEvent()
    object StopLoading: RegisterPageEvent()
}