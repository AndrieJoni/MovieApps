package stickearn.movie.stickearnmovieapps.view.movieHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.movie.domain.usecase.GetNowPlayingMovieListUseCase
import com.movie.domain.usecase.GetPopularMovieListUseCase
import com.movie.domain.usecase.GetTopRatedMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.movieHome.nowPlaying.NowPlayingMoviesDataSourceFactory
import stickearn.movie.stickearnmovieapps.view.movieHome.popular.PopularMoviesDataSourceFactory
import stickearn.movie.stickearnmovieapps.view.movieHome.topRated.TopRatedMoviesDataSourceFactory
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val getNowPlayingMovieListUseCase: GetNowPlayingMovieListUseCase
) : ViewModel() {

    val goToDetailMovieEvent = SingleLiveEvent<MovieHomeModel>()
    val goToFavoriteActivityEvent = SingleLiveEvent<Any>()

    var popularMoviesDataSourceFactory: PopularMoviesDataSourceFactory? = null
    var topRatedMoviesDataSourceFactory: TopRatedMoviesDataSourceFactory? = null
    var nowPlayingMoviesDataSourceFactory: NowPlayingMoviesDataSourceFactory? = null

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun initializePopularMoviesLiveData(): LiveData<PagedList<MovieHomeModel>> {

        popularMoviesDataSourceFactory = PopularMoviesDataSourceFactory(
            getPopularMovieListUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            popularMoviesDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    fun initializeTopRatedMoviesLiveData(): LiveData<PagedList<MovieHomeModel>> {

        topRatedMoviesDataSourceFactory = TopRatedMoviesDataSourceFactory(
            getTopRatedMovieListUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            topRatedMoviesDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    fun initializeNowPlayingMoviesLiveData(): LiveData<PagedList<MovieHomeModel>> {

        nowPlayingMoviesDataSourceFactory = NowPlayingMoviesDataSourceFactory(
            getNowPlayingMovieListUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            nowPlayingMoviesDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    fun movieClicked(movieData: MovieHomeModel) {
        goToDetailMovieEvent.postValue(movieData)
    }

    fun favoriteIconClicked() {
        goToFavoriteActivityEvent.call()
    }

    fun refreshPopularMovie() {
        popularMoviesDataSourceFactory?.popularMoviesDataSource?.invalidate()
    }

    fun refreshTopRatedMovie() {
        topRatedMoviesDataSourceFactory?.topRatedMoviesDataSource?.invalidate()
    }

    fun refreshNowPlayingMovie() {
        nowPlayingMoviesDataSourceFactory?.nowPlayingMoviesDataSource?.invalidate()
    }
}