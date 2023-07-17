package com.curvelo.playmovies.domain.repository

import com.curvelo.playmovies.data.remoto.MovieRepository
import com.curvelo.playmovies.domain.model.Movie

class SearchMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(query: String): List<Movie> {
        return movieRepository.searchMovies(query)
    }
}