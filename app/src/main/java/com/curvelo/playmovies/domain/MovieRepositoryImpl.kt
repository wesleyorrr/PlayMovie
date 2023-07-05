package com.curvelo.playmovies.domain

import com.curvelo.playmovies.data.ApiClient
import com.curvelo.playmovies.data.Movie
import com.curvelo.playmovies.data.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {
    private val movieApiService: MovieApiService = ApiClient.movieApi

    override suspend fun searchMovies(query: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = movieApiService.searchMovies(ApiClient.API_KEY, query)
            response.results
        }
    }
}