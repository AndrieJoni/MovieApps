package stickearn.movie.stickearnmovieapps.view.movieHome

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home_movie.*
import kotlinx.android.synthetic.main.layout_home_now_playing_movies.*
import kotlinx.android.synthetic.main.layout_home_popular_movies.*
import kotlinx.android.synthetic.main.layout_home_top_rated_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import stickearn.movie.stickearnmovieapps.R
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
            popularMoviesAdapter.submitList(it)
        })

        homeMovieViewModel.initializeTopRatedMoviesLiveData().observe(this, {
            topRatedMoviesAdapter.submitList(it)
        })

        homeMovieViewModel.initializeNowPlayingMoviesLiveData().observe(this, {
            nowPlayingMoviesAdapter.submitList(it)
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
    }
}