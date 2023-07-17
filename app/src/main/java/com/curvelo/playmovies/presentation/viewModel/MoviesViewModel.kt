package com.curvelo.playmovies.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curvelo.playmovies.domain.model.LoadingStatus
import com.curvelo.playmovies.domain.model.Movie

import com.curvelo.playmovies.domain.repository.GetPopularMoviesUseCase
import com.curvelo.playmovies.domain.repository.SearchMoviesUseCase

import kotlinx.coroutines.launch

class MoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                _loadingStatus.value = LoadingStatus.LOADING
                val result = searchMoviesUseCase(query)
                _movies.value = result
                _loadingStatus.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _loadingStatus.value = LoadingStatus.LOADING
                val result = getPopularMoviesUseCase()
                _movies.value = result
                _loadingStatus.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }
}
