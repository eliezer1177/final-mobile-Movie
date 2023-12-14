package com.d121211065.tmdb.data

import com.d121211065.tmdb.data.repository.FilmRepo
import com.d121211065.tmdb.data.service.ApiService

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val filmRepo: FilmRepo
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.themoviedb.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val filmRepo: FilmRepo
        get() = FilmRepo(retrofitService)

}