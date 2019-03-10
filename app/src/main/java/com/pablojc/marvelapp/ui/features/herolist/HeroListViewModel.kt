package com.pablojc.marvelapp.ui.features.herolist

import androidx.lifecycle.*
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.utils.DataError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroListViewModel(val heroListInteractor: GetHeroListInteractor) : ViewModel() {

    private lateinit var _heroesState: MediatorLiveData<ScreenState<HeroListState>>

    val heroListState: LiveData<ScreenState<HeroListState>>
        get() {
            if (!::_heroesState.isInitialized) {
                _heroesState = MediatorLiveData()
                _heroesState.value = ScreenState.Loading
                getHeroList()
            }
            return _heroesState
        }

    private fun getHeroList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){heroListInteractor.get()}
            result.either(::handleFailure,::handleHeroList)
        }
    }

    private fun handleHeroList(liveData: LiveData<List<HeroItemList>>) {
        _heroesState.addSource(liveData){
            if(it.isEmpty()){
                reloadList()
            }
            _heroesState.value = ScreenState.Render(HeroListState.ShowHeroList(it))
        }
    }

    fun reloadList() {
        viewModelScope.launch {
            _heroesState.value = ScreenState.Loading
            withContext(Dispatchers.IO){heroListInteractor.load()}
        }
    }

    private fun handleFailure(failure: DataError){
        _heroesState.value = ScreenState.Render(HeroListState.ShowError(
            when(failure) {
                is DataError.NetworkConnection -> R.string.error_network
                is DataError.ServerError -> R.string.error_server
                is DataError.DataNoValid -> R.string.error_data
            }))
    }

}