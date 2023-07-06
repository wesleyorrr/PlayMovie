package com.curvelo.playmovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.curvelo.playmovies.R
import com.curvelo.playmovies.databinding.ActivityMainBinding
import com.curvelo.playmovies.domain.MovieRepositoryImpl
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MoviesViewModel(MovieRepositoryImpl())
        adapter = MovieAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.movies.observe(this, Observer { movies ->
            adapter.setMovies(movies)

        })



        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchMovies(query)
            } else {
                Snackbar.make(binding.root, "Please enter a search query", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}