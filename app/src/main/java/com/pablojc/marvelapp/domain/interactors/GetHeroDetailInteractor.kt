package com.pablojc.marvelapp.domain.interactors

import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.utils.Either
import com.pablojc.marvelapp.utils.map
import com.pablojc.marvelapp.data.repositories.HeroesRepository
import com.pablojc.marvelapp.domain.mapped.toHero
import com.pablojc.marvelapp.utils.DataError
import javax.inject.Inject

interface GetHeroDetailInteractor{
    fun getHeroById(heroId: Long?) : Either<DataError, Hero>
}

class GetHeroDetailInteractorImpl @Inject constructor(val repository: HeroesRepository) : GetHeroDetailInteractor {

    override fun getHeroById(heroId: Long?): Either<DataError, Hero> {
        return repository.getHeroById(heroId)
                .map { it.toHero()}
    }
}