package com.d121211065.tmdb.data.service

import com.d121211065.tmdb.data.response.GetFilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/top_rated")
    suspend fun getFilm(
        @Query("api_key") api_key:  String = "ed2c3a992c4157b0cae56516ff1e0358",
        @Query("page") page: Int
    ): GetFilmResponse
}