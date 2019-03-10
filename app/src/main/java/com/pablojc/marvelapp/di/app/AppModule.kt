package com.pablojc.marvelapp.di.app

import android.app.Application
import android.content.Context
import dagger.Provides
import javax.inject.Singleton
import com.pablojc.marvelapp.data.net.retrofit.HeroApiClient
import com.pablojc.marvelapp.data.persistence.DBManager
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.data.repositories.HeroesRepositoryImpl

import dagger.Module

@Module
class AppModule(val context: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    internal fun provideDBManager(): DBManager {
        return DBManager(context)
    }

    @Provides
    @Singleton
    internal fun provideApiClient(): HeroApiClient {
        return HeroApiClient()
    }

    @Provides
    @Singleton
    internal fun provideHeroesRepository(dbManager: DBManager, apiClient: HeroApiClient): HeroesRepository {
        return HeroesRepositoryImpl(dbManager.getHeroesDao(),apiClient.getApiService())
    }

}