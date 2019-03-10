package com.pablojc.marvelapp.data.parsers

import com.pablojc.marvelapp.data.net.modelresponses.HeroListResponse
import com.pablojc.marvelapp.data.net.modelresponses.HeroResponse
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity

fun HeroListResponse.toHeroListEntity() = heroes.map { it.toHeroEntity() }

fun HeroResponse.toHeroEntity() = HeroEntity(
    name,
    photo,
    realName,
    height,
    power,
    abilities,
    groups
)