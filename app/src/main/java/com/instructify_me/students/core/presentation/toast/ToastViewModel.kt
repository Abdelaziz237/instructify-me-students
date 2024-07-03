package com.instructify_me.students.core.presentation.toast

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.R
import com.instructify_me.students.core.presentation.ui.theme.ErrorRed
import com.instructify_me.students.core.presentation.ui.theme.MidNightBlueLight
import com.instructify_me.students.core.presentation.ui.theme.SuccessGreen
import com.sanad.studentsapp.core.presentation.toast.ToastEvent
import com.sanad.studentsapp.core.presentation.toast.ToastState
import com.sanad.studentsapp.core.presentation.toast.ToastType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToastViewModel: ViewModel() {
    private val _state = mutableStateOf(ToastState())
    val state: State<ToastState> = _state

    fun onEvent(event: ToastEvent) {
        when(event) {
            is ToastEvent.ShowToast -> {
                val (backgroundColor, icon) = getToastStyle(event.type)
                _state.value = state.value.copy(
                    message = event.message,
                    type = event.type,
                    background = backgroundColor,
                    icon = icon,
                    isShown = true
                )
                viewModelScope.launch {
                    delay(3000)
                    onEvent(ToastEvent.DismissToast)
                }
            }
            is ToastEvent.DismissToast -> {
                _state.value = state.value.copy(
                    isShown = false,
                    type = null
                )
            }
        }
    }

    private fun getToastStyle(type: ToastType): Pair<Color, Int> {
            return when (type){
            ToastType.SuccessToast -> {
                Pair(SuccessGreen, R.drawable.ic_check_circle)
            }
            ToastType.InfoToast -> {
                Pair(MidNightBlueLight, R.drawable.baseline_info_24)
            }
            ToastType.ErrorToast -> {
                Pair(ErrorRed, R.drawable.ic_warning)
            }
        }
    }
}