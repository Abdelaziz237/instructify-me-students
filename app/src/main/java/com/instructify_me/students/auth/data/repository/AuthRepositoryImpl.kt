package com.instructify_me.students.auth.data.repository

import android.util.Log
import com.google.gson.Gson
import com.instructify_me.students.auth.data.dto.body.InterestsBodyDTO
import com.instructify_me.students.auth.data.dto.body.SignInBodyDTO
import com.instructify_me.students.auth.data.dto.body.UserForRegister
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.BAD_REQUEST
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.NOT_FOUND
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.UNKNOWN
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.BASE_URL
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.INTERESTS
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.PROFILE
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.SIGN_IN
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.SIGN_UP
import com.instructify_me.students.auth.data.source.objects.NetworkRoutes.STUDENT
import com.instructify_me.students.auth.domain.repository.AuthRepository
import com.instructify_me.students.core.domain.di.AppModule
import com.instructify_me.students.core.domain.di.AppModuleImpl
import com.instructify_me.students.core.domain.model.ApiResponse
import com.instructify_me.students.core.presentation.InstructifyMeApp.Companion.appModule
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

class AuthRepositoryImpl(
    private val ktorClient: HttpClient = appModule.ktorClient,
    private val gson: Gson = Gson(),
    private val authToken: String
): AuthRepository {

    @OptIn(InternalAPI::class)
    override suspend fun signIn(bodyValue: SignInBodyDTO): ApiResponse {
        val jsonBody = gson.toJson(bodyValue)
        return try {
            val response = ktorClient.post(BASE_URL + STUDENT + SIGN_IN) {
                contentType(ContentType.Application.Json)
                body = TextContent(jsonBody, ContentType.Application.Json)
            }

            Log.e("Auth", response.toString())
            Log.e("Auth", jsonBody)
            Log.e("Auth", response.status.value.toString())
            Log.e("Auth", response.bodyAsText())
            when (response.status.value) {
                in 200..300 -> {
                    ApiResponse.Success(
                        data = response.bodyAsText()
                    )
                }
                BAD_REQUEST -> {
                    ApiResponse.Failed(BAD_REQUEST)
                }
                NOT_FOUND -> {
                    ApiResponse.Failed(NOT_FOUND)
                }
                else -> {
                    ApiResponse.Failed(UNKNOWN)
                }
            }
        } catch (e: Exception) {
            // Handle errors here
            Log.e("Add", "error: ${e.message}")
            ApiResponse.Error
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun register(bodyValue: UserForRegister): ApiResponse {
        val jsonBody = gson.toJson(bodyValue)
        return try {
            val response = ktorClient.post(BASE_URL + STUDENT + SIGN_UP) {
                contentType(ContentType.Application.Json)
                body = TextContent(jsonBody, ContentType.Application.Json)
            }

            Log.e("Auth", response.toString())
            Log.e("Auth", jsonBody)
            Log.e("Auth", response.status.value.toString())
            Log.e("Auth", response.bodyAsText())
            when (response.status.value) {
                in 200..300 -> {
                    ApiResponse.Success(
                        data = response.bodyAsText()
                    )
                }
                BAD_REQUEST -> {
                    ApiResponse.Failed(BAD_REQUEST)
                }
                NOT_FOUND -> {
                    ApiResponse.Failed(NOT_FOUND)
                }
                else -> {
                    ApiResponse.Failed(UNKNOWN)
                }
            }
        } catch (e: Exception) {
            // Handle errors here
            Log.e("Add", "error: ${e.message}")
            ApiResponse.Error
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun setInterests(bodyValue: InterestsBodyDTO): ApiResponse {
        val jsonBody = gson.toJson(bodyValue)
        return try {
            val response = ktorClient.put(BASE_URL + STUDENT + PROFILE) {
                contentType(ContentType.Application.Json)
                header("authToken", authToken)
                body = TextContent(jsonBody, ContentType.Application.Json)
            }

            Log.e("Auth", response.toString())
            Log.e("Auth", jsonBody)
            Log.e("Auth", response.status.value.toString())
            Log.e("Auth", response.bodyAsText())
            when (response.status.value) {
                in 200..300 -> {
                    ApiResponse.Success(
                        data = response.bodyAsText()
                    )
                }
                BAD_REQUEST -> {
                    ApiResponse.Failed(BAD_REQUEST)
                }
                NOT_FOUND -> {
                    ApiResponse.Failed(NOT_FOUND)
                }
                else -> {
                    ApiResponse.Failed(UNKNOWN)
                }
            }
        } catch (e: Exception) {
            // Handle errors here
            Log.e("Add", "error: ${e.message}")
            ApiResponse.Error
        }
    }
    override suspend fun signOut(): ApiResponse {
        TODO("Not yet implemented")
    }

}