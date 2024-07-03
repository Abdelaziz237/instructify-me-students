package com.instructify_me.students.auth.domain.usecases

import com.instructify_me.students.auth.data.dto.body.UserForRegister
import com.instructify_me.students.auth.data.dto.response.SignUpResponseDTO
import com.instructify_me.students.auth.domain.repository.AuthRepository
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.usecases.DataValidator

class SignUpUseCase(
    private val repository: AuthRepository
) {
    private val validator: DataValidator<SignUpResponseDTO> = DataValidator(
        SignUpResponseDTO::class.java
    )

    suspend operator fun invoke(username: String, email: String, password: String): ValidationStatus<SignUpResponseDTO> {
        val body = UserForRegister(username, email, password)
        val response = repository.register(body)
        return validator.validateData(response)
    }
}
