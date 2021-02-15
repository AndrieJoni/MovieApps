package stickearn.movie.stickearnmovieapps.view.movieHome.nowPlaying

import androidx.paging.DataSource
import com.movie.domain.usecase.GetNowPlayingMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.PaginationStatus
import stickearn.movie.stickearnmovieapps.view.movieHome.MovieHomeModel

class NowPlayingMoviesDataSourceFactory(
    private val getNowPlayingMovieList: GetNowPlayingMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieHomeModel>() {

    var nowPlayingMoviesDataSource: NowPlayingMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieHomeModel> {

        nowPlayingMoviesDataSource = NowPlayingMoviesDataSource(
            paginationStatus,
            getNowPlayingMovieList,
            scope
        )

        return nowPlayingMoviesDataSource as NowPlayingMoviesDataSource
    }
}
