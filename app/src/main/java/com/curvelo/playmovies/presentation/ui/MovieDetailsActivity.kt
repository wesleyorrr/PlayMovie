package com.curvelo.playmovies.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curvelo.playmovies.R
import com.curvelo.playmovies.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieTitle = intent.getStringExtra("movie_title")
        val moviePosterPath = intent.getStringExtra("movie_poster_path")
        val descriptionTxt = intent.getStringExtra("description")

        binding.titleTextView.text = movieTitle
        binding.descriptionTextView.text = descriptionTxt

        val imageUrl = "https://image.tmdb.org/t/p/w500$moviePosterPath"
        Picasso.get().load(imageUrl).into(binding.posterImageView)

        binding.closeButton.setOnClickListener {
            finish()
        }
    }
}