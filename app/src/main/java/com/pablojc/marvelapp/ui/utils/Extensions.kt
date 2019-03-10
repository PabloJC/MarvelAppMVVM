package com.pablojc.marvelapp.ui.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}
inline fun <reified T : ViewModel> FragmentActivity.withViewModel(
    crossinline factory: () -> T,
    body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}


