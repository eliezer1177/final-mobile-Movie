package com.d121211065.tmdb.data.repository

import com.d121211065.tmdb.data.response.GetFilmResponse
import com.d121211065.tmdb.data.service.ApiService

class FilmRepo (private val apiService: ApiService){

    suspend fun getFilm(api_key:  String, page: Int): GetFilmResponse {
        return apiService.getFilm(api_key, page)
    }


}