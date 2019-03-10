package com.pablojc.marvelapp.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.mapped.toHeroList
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.utils.DataError
import com.pablojc.marvelapp.utils.Either
import com.pablojc.marvelapp.utils.map
import javax.inject.Inject

interface GetHeroListInteractor{
    fun get() : Either<DataError, LiveData<List<HeroItemList>>>
    fun load(): Either<DataError, Boolean>
}

class GetHeroListInteractorImpl @Inject constructor(val repository: HeroesRepository) : GetHeroListInteractor {

    override fun get(): Either<DataError, LiveData<List<HeroItemList>>> {
        return repository.getHeroList()
            .map { livedata -> Transformations.map(livedata){it.toHeroList()} }
    }

    override fun load(): Either<DataError, Boolean> {
        return repository.loadHeroList()
    }
}