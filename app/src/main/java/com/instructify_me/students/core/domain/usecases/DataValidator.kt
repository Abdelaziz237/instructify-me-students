package com.instructify_me.students.core.domain.usecases

import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.BAD_REQUEST
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.EMAIL_EXISTS
import com.instructify_me.students.auth.data.source.objects.NetworkFailedCodes.NOT_FOUND
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.ACCESS_DENIED
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.BAD_NETWORK
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.EMAIL_ALREADY_EXISTS
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.EMAIL_NOT_FOUND
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.PARSING_EXCEPTION
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.UNAUTHORIZED
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.UNDEFINED
import com.instructify_me.students.auth.data.source.objects.ValidationCodes.WRONG_PASSWORD
import com.instructify_me.students.core.domain.model.ApiResponse
import com.instructify_me.students.core.domain.model.ConversionResult
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.domain.util.decodeJsonFromString

class DataValidator<T>(
    private val dtoClass: Class<T>
) {
    fun validateData(response: ApiResponse, needsDecoding: Boolean = true): ValidationStatus<T> {
        val result: ConversionResult<T>
        return when(response) {
            is ApiResponse.Success -> {
                result = if (needsDecoding) decodeJsonFromString(response.data, dtoClass)
                else ConversionResult.Success("" as T)
                when (result) {
                    is ConversionResult.Success -> {
                        ValidationStatus.Valid(result.data)
                    }
                    is ConversionResult.Error -> {
                        ValidationStatus.NotValid(PARSING_EXCEPTION)
                    }
                }

            }
            is ApiResponse.Unauthorized -> {
                ValidationStatus.NotValid(UNAUTHORIZED)
            }
            is ApiResponse.Refused -> {
                ValidationStatus.NotValid(ACCESS_DENIED)
            }
            is ApiResponse.Failed -> {
                when(response.cause) {
                    BAD_REQUEST -> {
                        ValidationStatus.NotValid(WRONG_PASSWORD)
                    }
                    EMAIL_EXISTS -> {
                        ValidationStatus.NotValid(EMAIL_ALREADY_EXISTS)
                    }
                    NOT_FOUND -> {
                        ValidationStatus.NotValid(EMAIL_NOT_FOUND)
                    }
                    else -> {
                        ValidationStatus.NotValid(UNDEFINED)
                    }
                }
            }
            is ApiResponse.Error -> {
                ValidationStatus.NotValid(BAD_NETWORK)
            }
        }
    }
}