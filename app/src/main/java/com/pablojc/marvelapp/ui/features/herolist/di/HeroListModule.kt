package com.pablojc.marvelapp.ui.features.herolist.di

import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class HeroListModule {

    @Provides
    fun provideHeroListInteractor(repository: HeroesRepository): GetHeroListInteractor {
        return GetHeroListInteractorImpl(repository)
    }
}