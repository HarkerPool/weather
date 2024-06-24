package com.harkerpoul.weatherforecast.common

/**
 * A class that holds a loading signal or the result of an operation.
 */
sealed class BaseResult<out T> {
    data object Loading : BaseResult<Nothing>()
    data class Error(val errorMessage: String?) : BaseResult<Nothing>()
    data class Success<out T>(val data: T?) : BaseResult<T>()
}