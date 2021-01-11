package stickearn.movie.stickearnmovieapps.view.movieFavorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite_movie.*
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.database.MovieEntity

@AndroidEntryPoint
class FavoriteMovieActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels()

    private var movieFavoriteAdapter = FavoriteMovieAdapter()

    private var isFirstTimeLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)
        initView()
        initObserver()
        initEventListener()
    }

    private fun initView() {

        rvFavoriteMovie.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        rvFavoriteMovie.adapter = movieFavoriteAdapter
    }

    private fun initEventListener() {

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initObserver() {

        favoriteMovieViewModel.getFavoriteMoviesData().observe(this, {
            renderFavoriteMovieData(it)
        })
    }

    private fun renderFavoriteMovieData(favoriteMovieData: PagedList<MovieEntity>) {

        if (pbFavoriteMovies.isVisible) pbFavoriteMovies.isVisible = false

        if (isFirstTimeLoad && favoriteMovieData.size == 0) {
            tvNoFavoriteMovies.isVisible = true
            isFirstTimeLoad = false
        }

        movieFavoriteAdapter.submitList(favoriteMovieData)
    }
}