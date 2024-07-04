package com.instructify_me.students.portal.domain.repository

import com.instructify_me.students.core.domain.model.ApiResponse

interface TutorsRepository {
    suspend fun getRecommendedTutors(): ApiResponse
}