package com.capps.maad.data.network

sealed class APIResponse <out T: Any> {
    data class Success<out T : Any>(val value: T) : APIResponse<T>()
    data class Error(val message: String, val cause: Exception? = null, val code: Int? = null) : APIResponse<Nothing>()
}