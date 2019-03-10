package com.pablojc.marvelapp.data.exceptions.modelexceptions

sealed class DataError {
    object NetworkConnection : DataError()
    object ServerError : DataError()
}