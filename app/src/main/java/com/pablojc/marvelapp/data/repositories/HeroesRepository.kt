package com.pablojc.marvelapp.data.repositories

import androidx.lifecycle.LiveData
import com.pablojc.marvelapp.utils.DataError
import com.pablojc.marvelapp.utils.Either
import com.pablojc.marvelapp.utils.map
import com.pablojc.marvelapp.data.net.responses.HeroListResponse
import com.pablojc.marvelapp.data.net.retrofit.HeroApiService
import com.pablojc.marvelapp.data.parsers.toHeroListEntity
import com.pablojc.marvelapp.data.persistence.room.dao.HeroesDao
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity
import com.pablojc.marvelapp.data.utils.request

interface HeroesRepository {
    fun getHeroList(): Either<DataError, LiveData<List<HeroEntity>>>
    fun loadHeroList(): Either<DataError, Boolean>
    fun getHeroById(heroId: Long?): Either<DataError, HeroEntity>
}

class HeroesRepositoryImpl(val db: HeroesDao, val api: HeroApiService): HeroesRepository{

    override fun getHeroById(heroId: Long?): Either<DataError, HeroEntity> {
        heroId?.apply {
            return Either.Right(db.findHeroById(this))
        }
        return Either.Left(DataError.DataNoValid)
    }

    override fun loadHeroList(): Either<DataError, Boolean> {

        return request(api.getHeroList(), HeroListResponse())
            .map {
                db.updateHeroes(it.toHeroListEntity())
                true
            }
    }

    override fun getHeroList(): Either<DataError, LiveData<List<HeroEntity>>> {
        return Either.Right(db.getHeroes())
    }
}