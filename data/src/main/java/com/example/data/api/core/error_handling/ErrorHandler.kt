package com.example.data.api.core.error_handling


import android.os.Build
import androidx.annotation.RequiresExtension
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class ErrorHandler(val errorMessages: String, val code: Int? = null) {
    class Error(errorMessages: String, code: Int? = null) : ErrorHandler(errorMessages, code)
    companion object {
        fun fromException(exception: Throwable): ErrorHandler {
            return when (exception) {
                is HttpException -> handleHttpException(exception)
                is SocketTimeoutException -> Error(
                    errorMessages = ErrorMessages.connectionErrorMessage,
                )
                is IOException -> Error(
                    errorMessages = ErrorMessages.connectionErrorMessage,
                )
                else -> Error(errorMessages = ErrorMessages.unknownErrorMessage)
            }
        }

        private fun handleHttpException(exception: HttpException): ErrorHandler {
            return when (exception.code()) {
                StatuesCodes.UNAUTHORIZED, StatuesCodes.FORBIDDEN -> {
                    Error(
                        errorMessages = ErrorMessages.unauthorizedMessage,
                        code = StatuesCodes.UNAUTHORIZED
                    )
                }

                StatuesCodes.INTERNAL_SERVER_ERROR -> {
                    Error(
                        errorMessages = ErrorMessages.internalServerErrorMessage,
                        code = StatuesCodes.INTERNAL_SERVER_ERROR
                    )
                }

                StatuesCodes.NOT_FOUND -> {
                    Error(
                        errorMessages = ErrorMessages.notFoundMessage,
                        code = StatuesCodes.NOT_FOUND
                    )
                }

                StatuesCodes.CONFLICT -> {
                    Error(
                        errorMessages = ErrorMessages.conflictMessage,
                        code = StatuesCodes.CONFLICT
                    )
                }

                else -> {
                    Error(
                        errorMessages = ErrorMessages.unknownErrorMessage,
                        code = exception.code()
                    )
                }
            }
        }
    }
}