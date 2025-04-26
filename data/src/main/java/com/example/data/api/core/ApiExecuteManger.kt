package com.example.data.api.core

import com.example.data.api.core.error_handling.ErrorHandler
import com.example.domain.core.api_result.ApiResult
import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success

suspend fun <T> executeApi(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        val result = apiCall()
        Success(data = result)
    } catch (throwable: Throwable) {
        Failure(exceptionMessage = ErrorHandler.fromException(throwable).errorMessages)
    }
}
