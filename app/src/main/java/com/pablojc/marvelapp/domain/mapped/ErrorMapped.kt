package com.pablojc.marvelapp.domain.mapped

import com.pablojc.marvelapp.data.exceptions.modelexceptions.DataError
import com.pablojc.marvelapp.domain.models.Failure

fun DataError.toFailure() : Failure{
    return when(this){
        DataError.NetworkConnection -> Failure.NetworkConnection
        DataError.ServerError -> Failure.ServerError
    }
}