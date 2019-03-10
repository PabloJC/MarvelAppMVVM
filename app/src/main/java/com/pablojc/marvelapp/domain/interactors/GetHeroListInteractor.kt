package com.pablojc.marvelapp.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.data.functional.Either
import com.pablojc.marvelapp.data.functional.mapLeft
import com.pablojc.marvelapp.data.functional.mapRight
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.mapped.toFailure
import com.pablojc.marvelapp.domain.mapped.toHeroList
import com.pablojc.marvelapp.domain.models.Failure
import javax.inject.Inject

interface GetHeroListInteractor{
    fun get() : Either<Failure, LiveData<List<HeroItemList>>>
    fun load(): Either<Failure, Boolean>
}

class GetHeroListInteractorImpl @Inject constructor(val repository: HeroesRepository) : GetHeroListInteractor {

    override fun get(): Either<Failure, LiveData<List<HeroItemList>>> {
        return repository.getHeroList()
            .mapRight { livedata -> Transformations.map(livedata){it.toHeroList()} }
            .mapLeft { it.toFailure() }
    }

    override fun load(): Either<Failure, Boolean> {
        return repository.loadHeroList().mapLeft { it.toFailure() }
    }

}