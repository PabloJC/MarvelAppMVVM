package com.pablojc.marvelapp.utils

sealed class DataError {
    object NetworkConnection : DataError()
    object ServerError : DataError()
    object DataNoValid : DataError()
}