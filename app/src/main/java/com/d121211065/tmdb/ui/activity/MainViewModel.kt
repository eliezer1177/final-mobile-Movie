package com.d121211065.tmdb.ui.activity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211065.tmdb.MyApplication
import com.d121211065.tmdb.data.models.Result
import com.d121211065.tmdb.data.repository.FilmRepo

import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val movies: List<Result>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val filmRepo: FilmRepo): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getFilms() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = filmRepo.getFilm(
                "ed2c3a992c4157b0cae56516ff1e0358",
                1
            )
            mainUiState = MainUiState.Success(result.results.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getFilms()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val moviesRepository = application.container.filmRepo
                MainViewModel(moviesRepository)
            }
        }
    }
}