package com.instructify_me.students.core.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.core.domain.objects.LocalClient
import kotlinx.coroutines.launch

class SplashViewModel(private val client: LocalClient) : ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state


    fun hasValidUser() {
        viewModelScope.launch {
            val authToken = client.getAuthToken()
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


    class SplashViewModelFactory(private val client: LocalClient) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SplashViewModel(client = client) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}