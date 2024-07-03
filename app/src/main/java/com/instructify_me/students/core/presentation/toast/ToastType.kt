package com.sanad.studentsapp.core.presentation.toast

sealed class ToastType {
    object SuccessToast: ToastType()
    object InfoToast: ToastType()
    object ErrorToast: ToastType()
}