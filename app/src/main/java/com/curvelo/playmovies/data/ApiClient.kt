package com.curvelo.playmovies.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "7aed4e26d8019444cbfb66a23e500320"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}