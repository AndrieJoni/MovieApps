package com.example.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.ActivityHomeMovieBinding
import com.example.home.databinding.LayoutHomeNowPlayingMoviesBinding
import com.example.home.databinding.LayoutHomePopularMoviesBinding
import com.example.home.databinding.LayoutHomeTopRatedMoviesBinding
import com.example.home.popular.PopularMoviesAdapter
import com.example.util.PaginationStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMovieActivity : AppCompatActivity() {

    private val homeMovieViewModel: HomeMovieViewModel by viewModels()

    private lateinit var binding: ActivityHomeMovieBinding
    private lateinit var bindingTopRatedMovie: LayoutHomeTopRatedMoviesBinding
    private lateinit var bindingNowPlayingMovie: LayoutHomeNowPlayingMoviesBinding
    private lateinit var bindingPopularMovie: LayoutHomePopularMoviesBinding

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

    private var movieListener = movieListener()

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initBinding())
        bindingNowPlayingMovie = binding.nowPlayingMovie
        bindingPopularMovie = binding.popularMovie
        bindingTopRatedMovie = binding.topRatedMovie
        initView()
        initObserver()
        initEventListener()
    }

    private fun initBinding(): View {

        binding = ActivityHomeMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initView() {

        bindingPopularMovie.rvPopularMovies.adapter = popularMoviesAdapter
        bindingTopRatedMovie.rvTopRatedMovies.adapter = topRatedMoviesAdapter
        bindingNowPlayingMovie.rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter

        bindingPopularMovie.rvPopularMovies.addItemDecoration(recyclerViewDecoration)
        bindingTopRatedMovie.rvTopRatedMovies.addItemDecoration(recyclerViewDecoration)
        bindingNowPlayingMovie.rvNowPlayingMovies.addItemDecoration(recyclerViewDecoration)
    }

    private fun initObserver() {

        homeMovieViewModel.initializePopularMoviesLiveData().observe(this, {
            renderPopularMovieData(it)
        })

        homeMovieViewModel.initializeTopRatedMoviesLiveData().observe(this, {
            renderTopRatedMovieData(it)
        })

        homeMovieViewModel.initializeNowPlayingMoviesLiveData().observe(this, {
            renderNowPlayingMovieData(it)
        })

        homeMovieViewModel.popularMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            checkPopularMoviesPaginationStatus(it)
        })

        homeMovieViewModel.topRatedMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            checkTopRatedMoviesPaginationStatus(it)
        })

        homeMovieViewModel.nowPlayingMoviesDataSourceFactory?.paginationStatus?.observe(this, {
            checkNowPlayingMoviesPaginationStatus(it)
        })

        homeMovieViewModel.goToDetailMovieEvent.observe(this, {
            goToDetailMovieEvent(it)
        })

        homeMovieViewModel.goToFavoriteActivityEvent.observe(this, {
            goToFavoriteMovieEvent()
        })
    }

    private fun initEventListener() {

        popularMoviesAdapter.onMovieListener = movieListener
        topRatedMoviesAdapter.onMovieListener = movieListener
        nowPlayingMoviesAdapter.onMovieListener = movieListener

        binding.toolbar.menu.findItem(R.id.menuFavoriteHome).setOnMenuItemClickListener {
            favoriteIconClicked()
            true
        }

        bindingPopularMovie.btnRefreshPopularMovies.setOnClickListener {
            refreshPopularMovie()
        }

        bindingTopRatedMovie.btnRefreshTopRatedMovies.setOnClickListener {
            refreshTopRatedMovie()
        }

        bindingNowPlayingMovie.btnRefreshNowPlayingMovies.setOnClickListener {
            refreshNowPlayingMovie()
        }
    }

    private fun movieListener(): OnMovieListener {
        return OnMovieListener {
            moviesClicked(it)
        }
    }

    private fun favoriteIconClicked() {
        homeMovieViewModel.favoriteIconClicked()
    }

    private fun moviesClicked(movieData: MovieHomeModel) {
        homeMovieViewModel.movieClicked(movieData)
    }

    private fun goToDetailMovieEvent(movieData: MovieHomeModel) {

        /*   val intent = Intent(this, DetailMovieActivity::class.java)
           intent.putExtra(DetailMovieActivity.MOVIE_DATA, movieData)
   */
        startActivity(intent)
    }

    private fun goToFavoriteMovieEvent() {
        //      startActivity(Intent(this, FavoriteMovieActivity::class.java))
    }

    private fun refreshPopularMovie() {

        bindingPopularMovie.btnRefreshPopularMovies.isVisible = false
        bindingPopularMovie.pbPopularMovies.isVisible = true
        bindingPopularMovie.rvPopularMovies.isVisible = false

        homeMovieViewModel.refreshPopularMovie()
    }

    private fun refreshTopRatedMovie() {

        bindingTopRatedMovie.btnRefreshTopRatedMovies.isVisible = false
        bindingTopRatedMovie.pbTopRatedMovies.isVisible = true
        bindingTopRatedMovie.rvTopRatedMovies.isVisible = false

        homeMovieViewModel.refreshTopRatedMovie()
    }

    private fun refreshNowPlayingMovie() {

        bindingNowPlayingMovie.btnRefreshNowPlayingMovies.isVisible = false
        bindingNowPlayingMovie.pbNowPlayingMovies.isVisible = true
        bindingNowPlayingMovie.rvNowPlayingMovies.isVisible = false

        homeMovieViewModel.refreshNowPlayingMovie()
    }

    private fun renderPopularMovieData(movieDatas: PagedList<MovieHomeModel>) {

        if (bindingPopularMovie.pbPopularMovies.isVisible)
            bindingPopularMovie.pbPopularMovies.isVisible = false

        if (bindingPopularMovie.rvPopularMovies.isVisible.not())
            bindingPopularMovie.rvPopularMovies.isVisible = true

        popularMoviesAdapter.submitList(movieDatas)
    }

    private fun renderTopRatedMovieData(movieDatas: PagedList<MovieHomeModel>) {

        if (bindingTopRatedMovie.pbTopRatedMovies.isVisible)
            bindingTopRatedMovie.pbTopRatedMovies.isVisible = false

        if (bindingTopRatedMovie.rvTopRatedMovies.isVisible.not())
            bindingTopRatedMovie.rvTopRatedMovies.isVisible = true

        topRatedMoviesAdapter.submitList(movieDatas)
    }

    private fun renderNowPlayingMovieData(movieDatas: PagedList<MovieHomeModel>) {

        if (bindingNowPlayingMovie.pbNowPlayingMovies.isVisible)
            bindingNowPlayingMovie.pbNowPlayingMovies.isVisible = false

        if (!bindingNowPlayingMovie.rvNowPlayingMovies.isVisible.not())
            bindingNowPlayingMovie.rvNowPlayingMovies.isVisible = true

        nowPlayingMoviesAdapter.submitList(movieDatas)
    }

    private fun checkPopularMoviesPaginationStatus(paginationStatus: PaginationStatus) {

        when (paginationStatus) {
            is PaginationStatus.Error -> {
                bindingPopularMovie.rvPopularMovies.isVisible = false
                bindingPopularMovie.pbPopularMovies.isVisible = false
                bindingPopularMovie.btnRefreshPopularMovies.isVisible = true
            }
            else -> {
            }
        }
    }

    private fun checkTopRatedMoviesPaginationStatus(paginationStatus: PaginationStatus) {

        when (paginationStatus) {
            is PaginationStatus.Error -> {

                bindingTopRatedMovie.rvTopRatedMovies.isVisible = false
                bindingTopRatedMovie.pbTopRatedMovies.isVisible = false
                bindingTopRatedMovie.btnRefreshTopRatedMovies.isVisible = true
            }
            else -> {
            }
        }
    }

    private fun checkNowPlayingMoviesPaginationStatus(paginationStatus: PaginationStatus) {

        when (paginationStatus) {
            is PaginationStatus.Error -> {

                bindingNowPlayingMovie.rvNowPlayingMovies.isVisible = false
                bindingNowPlayingMovie.pbNowPlayingMovies.isVisible = false
                bindingNowPlayingMovie.btnRefreshNowPlayingMovies.isVisible = true
            }
            else -> {
            }
        }
    }
}