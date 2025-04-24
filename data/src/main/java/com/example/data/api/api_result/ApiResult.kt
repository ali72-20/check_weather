package com.example.data.api.api_result

sealed class ApiResult<T> {}
data class Success<T>(val data: T) : ApiResult<T>()
data class Failure<T>(val exception: Exception) : ApiResult<T>()