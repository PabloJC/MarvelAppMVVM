package com.pablojc.marvelapp.data.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMapRight(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }

fun <T, L, R> Either<L, R>.flatMapLeft(fn: (L) -> Either<T, R>): Either<T, R> =
    when (this) {
        is Either.Left -> fn(a)
        is Either.Right -> Either.Right(b)
    }

fun <T, L, R> Either<L, R>.mapRight(fn: (R) -> (T)): Either<L, T> = this.flatMapRight(fn.c(::right))

fun <T, L, R> Either<L, R>.mapLeft(fn: (L) -> (T)): Either<T, R> = this.flatMapLeft(fn.c(::left))

