package com.pablojc.marvelapp.data.persistence

import android.content.Context
import androidx.room.Room
import com.pablojc.marvelapp.data.persistence.room.HeroesDatabase
import com.pablojc.marvelapp.data.persistence.room.dao.HeroesDao

class DBManager(val context: Context){

    private fun getAppDatabase(): HeroesDatabase =
        Room.databaseBuilder(context.applicationContext, HeroesDatabase::class.java, "com.pablojc.marvelapp.db").build()


    fun getHeroesDao() : HeroesDao {
        return getAppDatabase().heroesDao()
    }

}