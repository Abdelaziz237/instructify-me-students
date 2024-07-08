package com.instructify_me.students.portal.data.repository

import android.util.Log
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.BASE_URL
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.HOME
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.INSTRUCTORS
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.STUDENT
import com.instructify_me.students.core.domain.model.ApiResponse
import com.instructify_me.students.core.presentation.InstructifyMeApp.Companion.appModule
import com.instructify_me.students.portal.domain.repository.TutorsRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TutorsRepositoryImpl(
    private val ktorClient: HttpClient = appModule.ktorClient,
    private val authToken: String
): TutorsRepository {
    override suspend fun getRecommendedTutors(): ApiResponse {
        return try {
            val response = ktorClient.get(BASE_URL + STUDENT + HOME + INSTRUCTORS) {
                contentType(ContentType.Application.Json)
                header("authorization", "student $authToken")
            }
            
            Log.e("Tutor", "student $authToken")

            Log.e("Auth", response.toString())
            Log.e("Auth", response.status.value.toString())
            Log.e("Auth", response.bodyAsText())
            when (response.status.value) {
                in 200..300 -> {
                    ApiResponse.Success(
                        data = response.bodyAsText()
                    )
                }
                NetworkFailedCodes.BAD_REQUEST -> {
                    ApiResponse.Failed(NetworkFailedCodes.BAD_REQUEST)
                }
                NetworkFailedCodes.NOT_FOUND -> {
                    ApiResponse.Failed(NetworkFailedCodes.NOT_FOUND)
                }
                else -> {
                    ApiResponse.Failed(NetworkFailedCodes.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            // Handle errors here
            Log.e("Add", "error: ${e.message}")
            ApiResponse.Error
        }
    }
}