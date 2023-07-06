package com.curvelo.playmovies.domain

import com.curvelo.playmovies.data.Movie


interface MovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
}