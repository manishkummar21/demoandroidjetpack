package com.manishk9.news99

sealed class ResultWrapper<out T>{
    object Loading:ResultWrapper<Nothing>()
    object NetWorkError:ResultWrapper<Nothing>()
    data class Success<T>(val data:T):ResultWrapper<T>()
    data class Error(val error:ErrorResponse?):ResultWrapper<Nothing>()
}
