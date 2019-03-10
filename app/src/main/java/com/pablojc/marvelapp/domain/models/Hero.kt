package com.pablojc.marvelapp.domain.models

data class Hero(val name: String?,
                val photo: String?,
                val realName: String?,
                val height: String?,
                val power: String?,
                val abilities: String?,
                val groups: List<String> = listOf())