package com.pablojc.marvelapp.data.parsers

import com.pablojc.marvelapp.data.net.responses.HeroListResponse
import com.pablojc.marvelapp.data.net.responses.HeroResponse
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity

fun HeroListResponse.toHeroListEntity() = heroes.map { it.toHeroEntity() }.filter { !it.name.isNullOrEmpty() }

fun HeroResponse.toHeroEntity() = HeroEntity(name, photo, realName, height, power, abilities, groups)
