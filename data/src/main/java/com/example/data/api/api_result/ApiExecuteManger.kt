package com.example.data.api.api_result

suspend fun <T> executeApi(apiCall: suspend () -> T): ApiResult<T> {
    try {
        val result = apiCall()
        return Success(data = result)
    } catch (e: Exception) {
        return Failure(exception = e)
    } catch (t: Throwable) {
        return Failure(exception = Exception("Unknown error", t))
    }
}