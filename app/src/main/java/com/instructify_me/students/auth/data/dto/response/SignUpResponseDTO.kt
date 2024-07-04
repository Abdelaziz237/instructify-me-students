package com.instructify_me.students.auth.data.dto.response

data class SignUpResponseDTO(
    val token: String,
    val refreshToken: String,
    val studentID: String
)
