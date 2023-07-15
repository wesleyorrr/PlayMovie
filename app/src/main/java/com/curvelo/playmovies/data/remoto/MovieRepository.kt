package com.curvelo.playmovies.data.remoto

import com.curvelo.playmovies.domain.model.Movie


interface MovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
}

