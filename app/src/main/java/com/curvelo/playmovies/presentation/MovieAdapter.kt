package com.curvelo.playmovies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curvelo.playmovies.data.Movie
import com.curvelo.playmovies.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter( private var movies: List<Movie> = emptyList()) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


   fun setMovies(movies: List<Movie>) {
      this.movies = movies
    notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.titleTextView.text = movie.title

            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Picasso.get().load(imageUrl).into(binding.posterImageView)
        }
    }
}