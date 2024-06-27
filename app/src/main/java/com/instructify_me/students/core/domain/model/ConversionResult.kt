package com.instructify_me.students.core.domain.model

sealed class ConversionResult<T> {
    data class Success<T>(val data: T) : ConversionResult<T>()
    data class Error<T>(val exception: String) : ConversionResult<T>()
}