package stickearn.movie.stickearnmovieapps.view.movieHome

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home_movie.*
import kotlinx.android.synthetic.main.layout_home_now_playing_movies.*
import kotlinx.android.synthetic.main.layout_home_popular_movies.*
import kotlinx.android.synthetic.main.layout_home_top_rated_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.utils.PaginationStatus
import stickearn.movie.stickearnmovieapps.view.movieDetails.DetailMovieActivity
import stickearn.movie.stickearnmovieapps.view.movieFavorite.FavoriteMovieActivity
import stickearn.movie.stickearnmovieapps.view.movieHome.popular.PopularMoviesAdapter

class HomeMovieActivity : AppCompatActivity() {

    private val homeMovieViewModel: HomeMovieViewModel by viewModel()

    private var popularMoviesAdapter = PopularMoviesAdapter()
    private var topRatedMoviesAdapter = MoviesAdapter()
    private var nowPlayingMoviesAdapter = MoviesAdapter()

    private var recyclerViewDecoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            outRect.top = resources
                .getDimension(R.dimen.base16).toInt()
            outRect.bottom = resources
                .getDimension(R.dimen.base16).toInt()
            outRect.left = (if (position == 0) resources
                .getDimension(R.dimen.base16) else resources
                .getDimension(R.dimen.base8)).toInt()
            outRect.right = (if (position == state.itemCount - 1) resources
                .getDimension(R.dimen.base16) else resources
                .getDimension(R.dimen.base8)).toInt()
        }
    }

    private var movieListener = OnMovieListener {
        homeMovieViewModel.movieClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movie)
        initView()
        initObserver()
        initEventListener()
    }

    private fun initView() {

        rvPopularMovies.adapter = popularMoviesAdapter
        rvTopRatedMovies.adapter = topRatedMoviesAdapter
        rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter

        rvPopularMovies.addItemDecoration(recyclerViewDecoration)
        rvTopRatedMovies.addItemDecoration(recyclerViewDecoration)
        rvNowPlayingMovies.addItemDecoration(recyclerViewDecoration)
    }

    private fun initObserver() {

        homeMovieViewModel.initializePopularMoviesLiveData().observe(this, {

            if (pbPopularMovies.isVisible) pbPopularMovies.isVisible = false
            if (!rvPopularMovies.isVisible) rvPopularMovies.isVisible = true

            popularMoviesAdapter.submitList(it)
        })

        homeMovieViewModel.initializeTopRatedMoviesLiveData().observe(this, {

            if (pbTopRatedMovies.isVisible) pbTopRatedMovies.isVisible = false
            if (!rvTopRatedMovies.isVisible) rvTopRatedMovies.isVisible = true

            topRatedMoviesAdapter.submitList(it)
        })

        homeMovieViewModel.initializeNowPlayingMoviesLiveData().observe(this, {

            if (pbNowPlayingMovies.isVisible) pbNowPlayingMovies.isVisible = false
            if (!rvNowPlayingMovies.isVisible) rvNowPlayingMovies.isVisible = true

            nowPlayingMoviesAdapter.submitList(it)
        })

        homeMovieViewModel.popularMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            when (it) {
                is PaginationStatus.Error -> {
                    rvPopularMovies.isVisible = false
                    pbPopularMovies.isVisible = false
                    btnRefreshPopularMovies.isVisible = true
                }
            }
        })

        homeMovieViewModel.topRatedMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            when (it) {
                is PaginationStatus.Error -> {

                    rvTopRatedMovies.isVisible = false
                    pbTopRatedMovies.isVisible = false
                    btnRefreshTopRatedMovies.isVisible = true
                }
            }
        })

        homeMovieViewModel.nowPlayingMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            when (it) {
                is PaginationStatus.Error -> {

                    rvNowPlayingMovies.isVisible = false
                    pbNowPlayingMovies.isVisible = false
                    btnRefreshNowPlayingMovies.isVisible = true
                }
            }
        })

        homeMovieViewModel.goToDetailMovieEvent.observe(this, {

            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.MOVIE_DATA, it)

            startActivity(intent)
        })

        homeMovieViewModel.goToFavoriteActivityEvent.observe(this, {
            startActivity(Intent(this, FavoriteMovieActivity::class.java))
        })
    }

    private fun initEventListener() {

        popularMoviesAdapter.onMovieListener = movieListener
        topRatedMoviesAdapter.onMovieListener = movieListener
        nowPlayingMoviesAdapter.onMovieListener = movieListener

        toolbar.menu.findItem(R.id.menuFavoriteHome).setOnMenuItemClickListener {
            homeMovieViewModel.favoriteIconClicked()
            true
        }

        btnRefreshPopularMovies.setOnClickListener {

            btnRefreshPopularMovies.isVisible = false
            pbPopularMovies.isVisible = true
            rvPopularMovies.isVisible = false

            homeMovieViewModel.refreshPopularMovie()
        }

        btnRefreshTopRatedMovies.setOnClickListener {

            btnRefreshTopRatedMovies.isVisible = false
            pbTopRatedMovies.isVisible = true
            rvTopRatedMovies.isVisible = false

            homeMovieViewModel.refreshTopRatedMovie()
        }

        btnRefreshNowPlayingMovies.setOnClickListener {

            btnRefreshNowPlayingMovies.isVisible = false
            pbNowPlayingMovies.isVisible = true
            rvNowPlayingMovies.isVisible = false

            homeMovieViewModel.refreshNowPlayingMovie()
        }
    }
}