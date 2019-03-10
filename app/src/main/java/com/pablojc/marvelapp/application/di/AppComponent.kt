package com.pablojc.marvelapp.application.di

import com.pablojc.marvelapp.application.HeroesApp
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