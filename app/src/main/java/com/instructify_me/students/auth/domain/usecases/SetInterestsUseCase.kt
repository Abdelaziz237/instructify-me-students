package com.instructify_me.students.auth.domain.usecases

import com.instructify_me.students.auth.data.dto.body.InterestsBodyDTO
import com.instructify_me.students.auth.data.dto.response.InterestsResponseDTO
import com.instructify_me.students.auth.domain.repository.AuthRepository
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.usecases.DataValidator


class SetInterestsUseCase(
    private val repository: AuthRepository
) {
    private val validator: DataValidator<InterestsResponseDTO> = DataValidator(
        InterestsResponseDTO::class.java
    )

    suspend operator fun invoke(interests: List<String>): ValidationStatus<InterestsResponseDTO> {
        val body = InterestsBodyDTO(interests = interests)
        val response = repository.setInterests(body)
        return validator.validateData(response)
    }
}