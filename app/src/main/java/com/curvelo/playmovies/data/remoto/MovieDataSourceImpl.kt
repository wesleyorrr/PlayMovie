package com.curvelo.playmovies.data.remoto

import com.curvelo.playmovies.domain.model.Movie

class MovieDataSourceImpl(private val movieApiService: MovieApiService) : MovieDataSource {
    override suspend fun searchMovies(query: String): List<Movie> {
        val response = movieApiService.searchMovies(ApiClient.API_KEY, query)
        return response.body()?.results ?: emptyList()
    }

    override suspend fun getPopularMovies(): List<Movie> {
        val response = movieApiService.getPopularMovies(ApiClient.API_KEY)
        return response.body()?.results ?: emptyList()
    }
}