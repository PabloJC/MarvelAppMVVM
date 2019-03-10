package com.pablojc.marvelapp.di.app

import com.pablojc.marvelapp.HeroesApp
import com.pablojc.marvelapp.data.net.retrofit.HeroApiClient
import com.pablojc.marvelapp.data.persistence.DBManager
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: HeroesApp)
    fun getApiClient(): HeroApiClient
    fun getDBManager(): DBManager
    fun getHeroesRepository(): HeroesRepository
}