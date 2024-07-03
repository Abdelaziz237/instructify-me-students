package com.sanad.studentsapp.core.presentation.toast

sealed class ToastEvent {
    data class ShowToast(val message: Int, val type: ToastType): ToastEvent()
    object DismissToast: ToastEvent()
}