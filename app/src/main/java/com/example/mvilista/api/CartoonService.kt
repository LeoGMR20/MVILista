package com.example.mvilista.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CartoonService {
    const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: CartoonApi = getRetrofit().create(CartoonApi::class.java)
}