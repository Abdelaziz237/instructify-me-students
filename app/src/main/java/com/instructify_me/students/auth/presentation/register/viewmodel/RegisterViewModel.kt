package com.instructify_me.students.auth.presentation.register.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.auth.domain.AuthRepo
import com.instructify_me.students.auth.domain.usecases.AuthUseCases
import com.instructify_me.students.auth.presentation.register.info.RegisterPageEvent
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.presentation.InstructifyMeApp
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    private val authUseCases = AuthUseCases(AuthRepo.getRepo())

    fun onEvent(event: RegisterPageEvent, callback: () -> Unit) {
        when (event) {
            is RegisterPageEvent.Register -> {
                viewModelScope.launch {
                    when(val result = authUseCases.signUp(username = event.username, email = event.email, password = event.password)) {
                        is ValidationStatus.Valid -> {
                            val userInfo = result.data
                            saveUserCredentials(userInfo.token, userInfo.refreshToken, userInfo.studentID)
                        }
                        is ValidationStatus.NotValid -> {  }
                    }
                }
                callback()
            }
            is RegisterPageEvent.StartLoading -> {
                _state.value = true
            }
            is RegisterPageEvent.StopLoading -> {
                _state.value = false
            }
        }
    }
}

private suspend fun saveUserCredentials(token: String, refreshToken: String, studentId: String) {
    val client = InstructifyMeApp.appModule.userClient
    client.setAuthToken(token)
    client.setRefreshToken(refreshToken)
    client.setStudentId(studentId)
}