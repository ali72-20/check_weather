package com.example.data.api.core.error_handling

class ErrorMessages private constructor(){
   companion object{
       const val badCertificateMessage = "A security error has occurred. Invalid certificate."
       const val connectionErrorMessage =
           "A connection error has occurred. Please check your internet."
       const val unknownErrorMessage = "Oops! Something went wrong. Our team is working on it"
       const val unauthorizedMessage = "Unauthorized access."
       const val conflictMessage = "A conflict has occurred with the server."
       const val notFoundMessage = "Requested resource not found."
       const val internalServerErrorMessage = "Server encountered an error. Please try again later."
       const val tokenNotProvided = "token not provided"
       const val jsonWebTokenErrorInvalidToken = "JsonWebTokenError: invalid token";
       const val invalidTokenMassage =
           "Sorry, you do not have the necessary permissions. Please log in again to continue"
       const val invalidTokenLoginAgain = "invalid token .. login again"
   }
}