package com.pablojc.marvelapp.data.net.responses

import com.google.gson.annotations.SerializedName

data class HeroResponse(@SerializedName("name") val name: String?,
                        @SerializedName("photo") val photo: String?,
                        @SerializedName("realName") val realName: String?,
                        @SerializedName("height") val height: String?,
                        @SerializedName("power") val power: String?,
                        @SerializedName("abilities") val abilities: String?,
                        @SerializedName("groups") val groups: String?)

data class HeroListResponse(@SerializedName("superheroes") val heroes: List<HeroResponse> = listOf())