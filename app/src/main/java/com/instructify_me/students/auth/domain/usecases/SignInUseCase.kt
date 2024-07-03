package com.instructify_me.students.auth.domain.usecases

import com.instructify_me.students.auth.data.dto.body.SignInBodyDTO
import com.instructify_me.students.auth.data.dto.response.SignInResponseDTO
import com.instructify_me.students.auth.domain.repository.AuthRepository
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.usecases.DataValidator


class SignInUseCase(
    private val repository: AuthRepository
) {
    private val validator: DataValidator<SignInResponseDTO> = DataValidator(
        SignInResponseDTO::class.java
    )

    suspend operator fun invoke(emailOrCode: String, password: String): ValidationStatus<SignInResponseDTO> {
        val body = SignInBodyDTO(emailOrCode, password)
        val response = repository.signIn(body)
        return validator.validateData(response)
    }
}