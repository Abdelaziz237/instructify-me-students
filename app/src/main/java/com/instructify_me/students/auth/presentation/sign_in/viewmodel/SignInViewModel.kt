package com.instructify_me.students.auth.presentation.sign_in.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.auth.domain.AuthRepo
import com.instructify_me.students.auth.domain.usecases.AuthUseCases
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.objects.LocalClient
import com.instructify_me.students.core.presentation.InstructifyMeApp.Companion.appModule
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {
    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    private val authUseCases = AuthUseCases(AuthRepo.getRepo())

    fun onEvent(event: LoginPageEvent, callback: () -> Unit) {
        when (event) {
            is LoginPageEvent.Login -> {
                viewModelScope.launch {
                    when(val result = authUseCases.signIn(event.email, event.password)) {
                        is ValidationStatus.Valid -> {
                            val userInfo = result.data
                            saveUserCredentials(userInfo.token, userInfo.refreshToken, userInfo.studentID)
                            callback()
                        }
                        is ValidationStatus.NotValid -> {
                        
                        }
                    }
                }
            }
            is LoginPageEvent.StartLoading -> {
                _state.value = true
            }
            is LoginPageEvent.StopLoading -> {
                _state.value = false
            }
        }
    }

    private suspend fun saveUserCredentials(token: String, refreshToken: String, studentId: String) {
        val client = appModule.userClient
        client.setAuthToken(token)
        client.setRefreshToken(refreshToken)
        client.setStudentId(studentId)
    }
}