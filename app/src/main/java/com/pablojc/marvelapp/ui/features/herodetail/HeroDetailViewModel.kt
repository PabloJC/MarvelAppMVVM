package com.pablojc.marvelapp.ui.features.herodetail

import androidx.lifecycle.*
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.utils.DataError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailViewModel (val heroId: Long?,val heroListInteractor: GetHeroDetailInteractor) : ViewModel() {

    private lateinit var _heroDetailState: MutableLiveData<HeroDetailState>

    val heroDetailState: LiveData<HeroDetailState>
        get() {
            if (!::_heroDetailState.isInitialized) {
                _heroDetailState = MediatorLiveData()
                getHero()
            }
            return _heroDetailState
        }

    private fun getHero() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){heroListInteractor.getHeroById(heroId)}
            result.either(::handleFailure,::handleHero)
        }
    }

    private fun handleHero(hero: Hero) {
        _heroDetailState.value = HeroDetailState.ShowHero(hero)
    }

    private fun handleFailure(failure: DataError){
        _heroDetailState.value = HeroDetailState.ShowError
    }

}