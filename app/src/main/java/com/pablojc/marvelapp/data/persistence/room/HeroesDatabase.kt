package com.pablojc.marvelapp.data.persistence.room

import androidx.room.RoomDatabase
import androidx.room.Database
import com.pablojc.marvelapp.data.persistence.room.dao.HeroesDao
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity


@Database(entities = [HeroEntity::class], version = 1, exportSchema = false)
abstract class HeroesDatabase : RoomDatabase() {
    abstract fun heroesDao(): HeroesDao
}