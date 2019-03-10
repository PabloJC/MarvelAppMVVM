package com.pablojc.marvelapp

import android.app.Application
import com.pablojc.marvelapp.di.app.AppComponent
import com.pablojc.marvelapp.di.app.AppModule
import com.pablojc.marvelapp.di.app.DaggerAppComponent

class HeroesApp: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

