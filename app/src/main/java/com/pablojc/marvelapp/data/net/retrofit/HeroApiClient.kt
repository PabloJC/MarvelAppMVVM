package com.pablojc.marvelapp.data.net.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HeroApiClient{

    fun getApiService() : HeroApiService{
        return retrofit().create(HeroApiService::class.java)
    }

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()


    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.myjson.com/bins/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}