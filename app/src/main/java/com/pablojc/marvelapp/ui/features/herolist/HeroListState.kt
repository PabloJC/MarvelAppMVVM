package com.pablojc.marvelapp.ui.features.herolist

import com.pablojc.marvelapp.domain.models.HeroItemList

sealed class HeroListState {
    class ShowHeroList(val items: List<HeroItemList>) : HeroListState()
    class ShowError(val messageRes: Int): HeroListState()
}