package com.instructify_me.students.core.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.auth.data.repository.AuthRepositoryImpl
import com.instructify_me.students.auth.domain.AuthRepo
import com.instructify_me.students.core.presentation.InstructifyMeApp.Companion.appModule
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state


    init {
        viewModelScope.launch {
            AuthRepo.setRepo(AuthRepositoryImpl(authToken = appModule.userClient.getAuthToken()))
        }
    }

    fun hasValidUser() {
        viewModelScope.launch {
            val authToken = appModule.userClient.getStudentId()
            if (authToken == "NULL") {
                _state.value = state.value.copy(
                    hasUser = false,
                    isLoading = false
                )
            } else {
                _state.value = state.value.copy(
                    hasUser = true,
                    isLoading = false
                )
            }
        }
    }
}