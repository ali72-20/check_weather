package com.example.data.api.api_result

abstract class ApiResult<T> {}

class Success<T>(val data:T) :ApiResult<T>()
class Failure<T>(val exception:Exception) :ApiResult<T>()

