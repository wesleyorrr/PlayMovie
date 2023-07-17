package com.curvelo.playmovies.data.remoto

import com.curvelo.playmovies.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource) : MovieRepository {
    override suspend fun searchMovies(query: String): List<Movie> {
        return movieDataSource.searchMovies(query)
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return movieDataSource.getPopularMovies()
    }
}
