package com.pablojc.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider


class ViewModelFactory @Inject constructor(private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mProviderMap.getValue(modelClass).get() as T
    }
}