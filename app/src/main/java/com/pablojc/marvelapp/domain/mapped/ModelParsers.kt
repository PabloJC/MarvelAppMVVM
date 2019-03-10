package com.pablojc.marvelapp.domain.mapped

import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.domain.models.HeroItemList

fun HeroEntity.toHero() = Hero(name!!, photo, realName, height, power, abilities, groups)

fun HeroEntity.toHeroItemList() = HeroItemList(id, name!!, photo, groups)

fun List<HeroEntity>.toHeroList() = map { it.toHeroItemList() }
