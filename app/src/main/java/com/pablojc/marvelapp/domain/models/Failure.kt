package com.pablojc.marvelapp.domain.models

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object NoValid: Failure()
}