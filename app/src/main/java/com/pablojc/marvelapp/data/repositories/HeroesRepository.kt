package com.pablojc.marvelapp.data.repositories

import androidx.lifecycle.LiveData
import com.pablojc.marvelapp.application.HeroesApp
import com.pablojc.marvelapp.data.exceptions.modelexceptions.DataError
import com.pablojc.marvelapp.data.functional.Either
import com.pablojc.marvelapp.data.functional.mapRight
import com.pablojc.marvelapp.data.net.modelresponses.HeroListResponse
import com.pablojc.marvelapp.data.net.retrofit.HeroApiService
import com.pablojc.marvelapp.data.parsers.toHeroListEntity
import com.pablojc.marvelapp.data.persistence.room.dao.HeroesDao
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity
import retrofit2.Call

interface HeroesRepository {
    fun getHeroList(): Either<DataError, LiveData<List<HeroEntity>>>
    fun loadHeroList(): Either<DataError, Boolean>
    fun getHeroById(heroId: Long): Either<DataError, LiveData<HeroEntity>>
}

class HeroesRepositoryImpl(val db: HeroesDao, val api: HeroApiService): HeroesRepository{

    override fun getHeroById(heroId: Long): Either<DataError, LiveData<HeroEntity>> {
        return Either.Right(db.findHeroById(heroId))
    }

    override fun loadHeroList(): Either<DataError, Boolean> {
        return request(api.getHeroList(), HeroListResponse())
            .mapRight {
                db.updateHeroes(it.toHeroListEntity())
                true
            }
    }

    override fun getHeroList(): Either<DataError, LiveData<List<HeroEntity>>> {
        return Either.Right(db.getHeroes())
    }


    private fun <R> request(call: Call<R>, default: R): Either<DataError, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(DataError.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(DataError.ServerError)
        }
    }
}