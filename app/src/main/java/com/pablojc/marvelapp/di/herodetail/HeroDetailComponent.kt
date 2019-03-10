package com.pablojc.marvelapp.di.herodetail

import com.pablojc.marvelapp.di.app.AppComponent
import com.pablojc.marvelapp.ui.base.ActivityScope
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [HeroDetailModule::class])
interface HeroDetailComponent {
    fun inject(activity: HeroDetailActivity)
    fun getInteractor(): GetHeroDetailInteractor
}