package com.pablojc.marvelapp.ui.features.herodetail

import androidx.lifecycle.*
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.domain.models.Failure
import com.pablojc.marvelapp.domain.models.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor(val heroId: Long?,val heroListInteractor: GetHeroDetailInteractor) : ViewModel() {

    var heroesState = MediatorLiveData<ScreenState<HeroDetailState>>()

    init {
        getHero()
    }

    private fun getHero() {
        viewModelScope.launch {
            heroesState.value = ScreenState.Loading
            val result = withContext(Dispatchers.IO){heroListInteractor.getHeroById(heroId)}
            result.either(::handleFailure,::handleHero)
        }
    }

    private fun handleHero(liveData: LiveData<Hero>) {
        heroesState.addSource(liveData){
            viewModelScope.launch {
                heroesState.value = ScreenState.ShowSuccess(HeroDetailState.ShowHero(it))
            }
        }
    }

    private fun handleFailure(failure: Failure){
        viewModelScope.launch {
            heroesState.value = ScreenState.ShowError(failure)
        }
    }

}