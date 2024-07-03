package com.instructify_me.students.auth.presentation.sign_in.info

sealed class LoginPageEvent {
    data class Login(val email: String, val password: String): LoginPageEvent()
    object StartLoading: LoginPageEvent()
    object StopLoading: LoginPageEvent()
}