package com.example.data.api.error_handling

class StatuesCodes  private constructor(){
    companion object{
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val CONFLICT = 409
        const val NOT_FOUND = 404
        const val INTERNAL_SERVER_ERROR = 500
    }

}