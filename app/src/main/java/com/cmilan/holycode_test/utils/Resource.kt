package com.cmilan.holycode_test.utils

sealed class Resource<T>(val data: T?, val e: Exception?) {

    class Success<T>(data: T) : Resource<T>(data, null)

    class Error<T>(e: Exception) : Resource<T>(null, e)
}
