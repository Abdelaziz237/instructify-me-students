package com.instructify_me.students.auth.data.dto.body

data class UserForRegister(
    val name: String,
    val email:String,
    val password: String,
)