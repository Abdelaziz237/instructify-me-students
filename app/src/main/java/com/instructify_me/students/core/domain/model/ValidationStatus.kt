package com.instructify_me.students.core.domain.model


sealed class ValidationStatus<T> {
    data class Valid<T>(val data: T): ValidationStatus<T>()
    data class NotValid<T>(val cause: Int): ValidationStatus<T>()
}