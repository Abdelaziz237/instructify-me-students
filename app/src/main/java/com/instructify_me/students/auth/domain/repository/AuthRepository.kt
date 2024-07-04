package com.instructify_me.students.auth.domain.repository

import com.instructify_me.students.auth.data.dto.body.InterestsBodyDTO
import com.instructify_me.students.auth.data.dto.body.SignInBodyDTO
import com.instructify_me.students.auth.data.dto.body.UserForRegister
import com.instructify_me.students.core.domain.model.ApiResponse

interface AuthRepository {
    suspend fun signIn(bodyValue: SignInBodyDTO): ApiResponse
    suspend fun register(bodyValue: UserForRegister): ApiResponse
    suspend fun setInterests(bodyValue: InterestsBodyDTO): ApiResponse
    suspend fun signOut(): ApiResponse

}