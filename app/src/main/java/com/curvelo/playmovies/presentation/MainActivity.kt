package com.curvelo.playmovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.curvelo.playmovies.LoadingStatus
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

        viewModel.loadingStatus.observe(this, Observer { status ->
            when (status) {
                LoadingStatus.LOADING -> showProgressBar()
                LoadingStatus.SUCCESS -> hideProgressBar()
                LoadingStatus.ERROR -> showError()
            }
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

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        Snackbar.make(binding.root, "Error occurred. Please try again later.", Snackbar.LENGTH_SHORT).show()
    }
}