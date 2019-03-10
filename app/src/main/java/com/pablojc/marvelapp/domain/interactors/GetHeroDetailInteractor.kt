package com.pablojc.marvelapp.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.data.functional.Either
import com.pablojc.marvelapp.data.functional.mapLeft
import com.pablojc.marvelapp.data.functional.mapRight
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.mapped.toFailure
import com.pablojc.marvelapp.domain.mapped.toHero
import com.pablojc.marvelapp.domain.models.Failure
import javax.inject.Inject

interface GetHeroDetailInteractor{
    fun getHeroById(heroId: Long?) : Either<Failure, LiveData<Hero>>
}

class GetHeroDetailInteractorImpl @Inject constructor(val repository: HeroesRepository) : GetHeroDetailInteractor {

    override fun getHeroById(heroId: Long?): Either<Failure, LiveData<Hero>> {
        heroId?.apply {
            return repository.getHeroById(this)
                .mapRight { Transformations.map(it){it.toHero()}}
                .mapLeft { it.toFailure() }
        }
        return Either.Left(Failure.NoValid)
    }
}