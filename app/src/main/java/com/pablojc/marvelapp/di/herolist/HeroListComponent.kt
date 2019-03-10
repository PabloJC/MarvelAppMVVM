package com.pablojc.marvelapp.di.herolist

import com.pablojc.marvelapp.di.app.AppComponent
import com.pablojc.marvelapp.ui.base.ActivityScope
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.ui.adapters.HeroListAdapter
import com.pablojc.marvelapp.ui.features.herolist.HeroListActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [HeroListModule::class])
interface HeroListComponent {
    fun inject(activity: HeroListActivity)
    fun getInteractor(): GetHeroListInteractor
    fun getAdapter(): HeroListAdapter
}