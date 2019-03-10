package com.pablojc.marvelapp.data.net.retrofit

import com.pablojc.marvelapp.data.net.responses.HeroListResponse
import retrofit2.Call
import retrofit2.http.GET

interface HeroApiService{
    @GET("bvyob")
    fun getHeroList(): Call<HeroListResponse>
}
