package com.pablojc.marvelapp.domain.models

data class HeroItemList(val id: Long,
                        val name: String?,
                        val photo: String?,
                        val groups: List<String> = listOf())