package com.pablojc.marvelapp.ui.features.herolist

import androidx.lifecycle.*
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.models.Failure
import com.pablojc.marvelapp.domain.models.HeroItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroListViewModel @Inject constructor(val heroListInteractor: GetHeroListInteractor) : ViewModel() {

    var heroesState = MediatorLiveData<ScreenState<HeroListState>>()

    init {
        getHeroList()
    }

    private fun getHeroList() {
        viewModelScope.launch {
            heroesState.value = ScreenState.Loading
            val result = withContext(Dispatchers.IO){heroListInteractor.get()}
            result.either(::handleFailure,::handleHeroList)
        }
    }

    private fun handleHeroList(liveData: LiveData<List<HeroItemList>>) {
        heroesState.addSource(liveData){
            if(it.isEmpty()){
                reloadList()
            }
            viewModelScope.launch {
                heroesState.value = ScreenState.ShowSuccess(HeroListState.ShowHeroList(it))
            }
        }
    }

    fun reloadList() {
        viewModelScope.launch {
            heroesState.value = ScreenState.Loading
            val result = withContext(Dispatchers.IO){heroListInteractor.load()}
            result.either(::handleFailure,::handleListUpdated)
        }
    }

    private fun handleListUpdated(listUpdated: Boolean) {
        if(listUpdated){
        }
    }

    private fun handleFailure(failure: Failure){
        viewModelScope.launch {
            heroesState.value = ScreenState.ShowError(failure)
        }
    }

}