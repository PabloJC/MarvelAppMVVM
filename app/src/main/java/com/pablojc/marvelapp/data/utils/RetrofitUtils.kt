package com.pablojc.marvelapp.data.utils

import com.pablojc.marvelapp.utils.DataError
import com.pablojc.marvelapp.utils.Either
import retrofit2.Call

fun <R> request(call: Call<R>, default: R): Either<DataError, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(response.body() ?: default)
            false -> Either.Left(DataError.ServerError)
        }
    } catch (exception: Throwable) {
        Either.Left(DataError.ServerError)
    }
}