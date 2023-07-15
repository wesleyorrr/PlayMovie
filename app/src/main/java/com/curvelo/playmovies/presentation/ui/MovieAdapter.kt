package com.curvelo.playmovies.presentation.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curvelo.playmovies.domain.model.Movie
import com.curvelo.playmovies.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movies: List<Movie> = emptyList()
    private lateinit var context: Context

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
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
            binding.descriptionTextView.text = movie.overview
            binding.releaseDateTextView.text = movie.release_date


            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Picasso.get().load(imageUrl).into(binding.posterImageView)

            binding.cardView.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                    putExtra("movie_title", movie.title)
                    putExtra("movie_poster_path", movie.poster_path)
                    putExtra("description", movie.overview)
                    putExtra("date", movie.release_date)



                    // Adicione aqui outros dados do filme que você deseja passar para a MovieDetailsActivity
                }
                context.startActivity(intent)
            }
        }
    }
}