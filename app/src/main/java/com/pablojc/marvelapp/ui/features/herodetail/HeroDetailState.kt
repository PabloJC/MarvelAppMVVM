package com.pablojc.marvelapp.ui.features.herodetail

import com.pablojc.marvelapp.domain.models.Hero

sealed class HeroDetailState {
    class ShowHero(val items: Hero) : HeroDetailState()
}