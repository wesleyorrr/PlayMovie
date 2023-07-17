package com.curvelo.playmovies.domain.repository

import com.curvelo.playmovies.data.remoto.MovieRepository
import com.curvelo.playmovies.domain.model.Movie

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMovies()
    }
}