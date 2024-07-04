package com.instructify_me.students.auth.data.dto.response

data class InterestsResponseDTO(
    val _id: String,
    val name: String,
    val email: String,
    val password: String,
    val status: String,
    val interests: List<Interest>,
    val education: List<String>
)


data class Interest(
    val _id: String,
    val tag: String
)