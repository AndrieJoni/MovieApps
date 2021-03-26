package com.example.moviefavorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.commonui.MovieModel
import com.example.moviefavorite.databinding.ActivityFavoriteMovieBinding
import com.example.util.PaginationStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels()

    private lateinit var binding: ActivityFavoriteMovieBinding

    private var movieFavoriteAdapter = FavoriteMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initBinding())
        initView()
        initObserver()
        initEventListener()
    }

    private fun initBinding(): View {

        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initView() {

        binding.rvFavoriteMovie.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.rvFavoriteMovie.adapter = movieFavoriteAdapter
    }

    private fun initEventListener() {

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initObserver() {

        favoriteMovieViewModel.getFavoriteMoviesData().observe(this, {
            renderFavoriteMovieData(it)
        })

        favoriteMovieViewModel.favoriteMovieDataSourceFactory?.paginationStatus
            ?.observe(this,
                {

                    when (it) {

                        is PaginationStatus.Empty -> {
                            binding.pbFavoriteMovies.isVisible = false
                            binding.tvNoFavoriteMovies.isVisible = true
                        }

                        else -> {
                        }
                    }
                })
    }

    private fun renderFavoriteMovieData(favoriteMovieData: PagedList<MovieModel>) {

        binding.pbFavoriteMovies.isVisible = false
        binding.tvNoFavoriteMovies.isVisible = false

        movieFavoriteAdapter.submitList(favoriteMovieData)
    }
}