package com.example.home.nowPlaying

import androidx.paging.DataSource
import com.example.commonui.MovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetNowPlayingMovieListUseCase
import kotlinx.coroutines.CoroutineScope

class NowPlayingMoviesDataSourceFactory(
    private val getNowPlayingMovieList: GetNowPlayingMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieModel>() {

    var nowPlayingMoviesDataSource: NowPlayingMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieModel> {

        nowPlayingMoviesDataSource = NowPlayingMoviesDataSource(
            paginationStatus,
            getNowPlayingMovieList,
            scope
        )

        return nowPlayingMoviesDataSource as NowPlayingMoviesDataSource
    }
}
