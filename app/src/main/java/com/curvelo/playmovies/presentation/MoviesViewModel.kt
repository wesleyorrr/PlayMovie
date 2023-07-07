package com.curvelo.playmovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curvelo.playmovies.LoadingStatus
import com.curvelo.playmovies.data.Movie
import com.curvelo.playmovies.domain.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> get() = _loadingStatus

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        _loadingStatus.value = LoadingStatus.LOADING
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = movieRepository.getPopularMovies()
                _movies.value = result
                _loadingStatus.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun searchMovies(query: String) {
        _loadingStatus.value = LoadingStatus.LOADING
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = movieRepository.searchMovies(query)
                _movies.value = result
                _loadingStatus.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }
}