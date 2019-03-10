package com.pablojc.marvelapp.domain.mapped

import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.data.persistence.room.entities.HeroEntity
import com.pablojc.marvelapp.ui.utils.splitBy

fun getGroupsByString(groupsString: String?) : List<String> {
    val list = mutableListOf<String>()
    groupsString?.apply {
        list.addAll(splitBy(","))
    }
    return list
}

fun HeroEntity.toHero() =
    Hero(
        name,
        photo,
        realName,
        height,
        power,
        abilities,
        getGroupsByString(groups)
    )
fun HeroEntity.toHeroItemList() =
    HeroItemList(id, name, photo, getGroupsByString(groups))


fun List<HeroEntity>.toHeroList() = map { it.toHeroItemList() }
