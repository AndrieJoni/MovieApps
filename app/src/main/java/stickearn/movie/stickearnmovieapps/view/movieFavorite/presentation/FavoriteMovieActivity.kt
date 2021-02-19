package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import stickearn.movie.stickearnmovieapps.databinding.ActivityFavoriteMovieBinding
import com.example.basedata.local.FavoriteMovieEntity

@AndroidEntryPoint
class FavoriteMovieActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels()

    private lateinit var binding: ActivityFavoriteMovieBinding

    private var movieFavoriteAdapter = FavoriteMovieAdapter()

    private var isFirstTimeLoad = true

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
    }

    private fun renderFavoriteMovieData(favoriteFavoriteMovieData: PagedList<com.example.basedata.local.FavoriteMovieEntity>) {

        if (binding.pbFavoriteMovies.isVisible)
            binding.pbFavoriteMovies.isVisible = false

        if (isFirstTimeLoad && favoriteFavoriteMovieData.size == 0) {
            binding.tvNoFavoriteMovies.isVisible = true
            isFirstTimeLoad = false
        }

        movieFavoriteAdapter.submitList(favoriteFavoriteMovieData)
    }
}