package com.pablojc.marvelapp.ui.base

import com.pablojc.marvelapp.domain.models.Failure

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    class ShowSuccess<T>(val renderState: T) : ScreenState<T>()
    class ShowError(val error: Failure): ScreenState<Nothing>()
}