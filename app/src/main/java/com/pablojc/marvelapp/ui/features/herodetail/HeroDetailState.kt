package com.pablojc.marvelapp.ui.features.herodetail

import com.pablojc.marvelapp.domain.models.Hero

sealed class HeroDetailState {
    class ShowHero(val hero: Hero) : HeroDetailState()
    object ShowError : HeroDetailState()
}