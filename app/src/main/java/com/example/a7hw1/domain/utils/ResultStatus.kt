package com.example.a7hw1.domain.utils

sealed class ResultStatus<T>(
    val data: T? = null,
    val error: String = "Unknown error"
) {
    class Loading<T>() : ResultStatus<T>()
    class Success<T>(data: T?) : ResultStatus<T>()
    class Error<T>(message: String?) : ResultStatus<T>()
}
