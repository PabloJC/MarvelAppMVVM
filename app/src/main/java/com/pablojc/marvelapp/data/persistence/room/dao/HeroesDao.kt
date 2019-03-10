package com.pablojc.marvelapp.data.persistence.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity

@Dao
interface HeroesDao{

    @Query("SELECT * FROM heroe")
    fun getHeroes(): LiveData<List<HeroEntity>>

    @Insert(onConflict = REPLACE)
    fun updateHeroes(heroes: List<HeroEntity>)

    @Query("SELECT * FROM heroe WHERE id = :heroId")
    fun findHeroById(heroId: Long): HeroEntity
}