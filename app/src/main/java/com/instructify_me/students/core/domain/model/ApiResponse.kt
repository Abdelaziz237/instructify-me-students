package com.instructify_me.students.core.domain.model


sealed class ApiResponse {
    data class Success(val data: String) : ApiResponse()

    object Error : ApiResponse()
    data class Failed(val cause: Int) : ApiResponse()
    object Refused : ApiResponse()
    object Unauthorized : ApiResponse()
}