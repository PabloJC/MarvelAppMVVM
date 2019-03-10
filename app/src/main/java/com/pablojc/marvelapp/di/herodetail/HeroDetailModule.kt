package com.pablojc.marvelapp.di.herodetail

import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractorImpl
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class HeroDetailModule {

    @Provides
    fun provideHeroDetailInteractor(repository: HeroesRepository): GetHeroDetailInteractor {
        return GetHeroDetailInteractorImpl(repository)
    }
}