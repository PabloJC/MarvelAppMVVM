package com.pablojc.marvelapp.application

import android.app.Application
import com.pablojc.marvelapp.application.di.AppComponent
import com.pablojc.marvelapp.application.di.AppModule
import com.pablojc.marvelapp.application.di.DaggerAppComponent

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

