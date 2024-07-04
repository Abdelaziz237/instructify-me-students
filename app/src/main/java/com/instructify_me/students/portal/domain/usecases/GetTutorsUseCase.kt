package com.instructify_me.students.portal.domain.usecases

import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.usecases.DataValidator
import com.instructify_me.students.portal.data.dto.TutorsResponseDTO
import com.instructify_me.students.portal.domain.repository.TutorsRepository

class GetTutorsUseCase(
    private val repository: TutorsRepository
) {
    private val validator: DataValidator<TutorsResponseDTO> = DataValidator(
        TutorsResponseDTO::class.java
    )

    suspend operator fun invoke(): ValidationStatus<TutorsResponseDTO> {
        val response = repository.getRecommendedTutors()
        return validator.validateData(response)
    }
}