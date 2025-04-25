package com.example.data.api.core

import com.example.data.api.core.error_handling.ErrorHandler
import com.example.domain.core.api_result.ApiResult
import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success

suspend fun <T> executeApi(apiCall: suspend () -> T): ApiResult<T> {
    try {
        val result = apiCall()
        return Success(data = result)
    } catch (e: Exception) {
        return Failure(exceptionMessage = ErrorHandler.fromException(e).errorMessages)
    } catch (t: Throwable) {
        return Failure(exceptionMessage = "Unknown error occurred")
    }
}