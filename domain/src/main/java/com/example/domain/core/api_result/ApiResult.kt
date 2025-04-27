package com.example.domain.core.api_result
sealed class ApiResult<T> {

}
data class Success<T>(val data: T) : ApiResult<T>()
data class Failure<T>(val exceptionMessage: String) : ApiResult<T>()