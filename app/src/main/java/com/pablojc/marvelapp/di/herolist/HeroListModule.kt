package com.pablojc.marvelapp.di.herolist

import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractorImpl
import com.pablojc.marvelapp.ui.adapters.HeroListAdapter
import com.pablojc.marvelapp.ui.features.herolist.HeroListActivity
import dagger.Module
import dagger.Provides

@Module
class HeroListModule(val context: HeroListActivity) {

    @Provides
    fun provideHeroListInteractor(repository: HeroesRepository): GetHeroListInteractor {
        return GetHeroListInteractorImpl(repository)
    }

    @Provides
    fun provideAdapter(): HeroListAdapter {
        return HeroListAdapter(context)
    }
}