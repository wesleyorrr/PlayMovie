package com.curvelo.playmovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curvelo.playmovies.data.Movie
import com.curvelo.playmovies.domain.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        // Carrega os filmes iniciais ao abrir o aplicativo
        loadMovies()
    }

    private fun loadMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = movieRepository.getPopularMovies()
            _movies.value = result
        }
    }

    fun searchMovies(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = movieRepository.searchMovies(query)
            _movies.value = result
        }
    }
}