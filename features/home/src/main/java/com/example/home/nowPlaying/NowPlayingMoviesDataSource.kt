package com.example.home.nowPlaying

import androidx.paging.PageKeyedDataSource
import com.example.commonui.MovieModel
import com.example.commonui.toMovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetNowPlayingMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NowPlayingMoviesDataSource(
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val getNowPlayingMovieListUseCase: GetNowPlayingMovieListUseCase,
    private val scope: CoroutineScope

) : PageKeyedDataSource<Int, MovieModel>() {

    private suspend fun getNowPlayingMovieData(
        page: String
    ) = getNowPlayingMovieListUseCase.invoke(page)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getNowPlayingMovieData("1")

                withContext(Dispatchers.Main) {

                    if (response.isEmpty()) {
                        paginationStatus.postValue(PaginationStatus.Empty)
                    } else {
                        callback.onResult(response.map { it.toMovieModel() }, null, 2)
                    }

                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getNowPlayingMovieData(params.key.toString())

                withContext(Dispatchers.Main) {
                    callback.onResult(response.map { it.toMovieModel() }, params.key + 1)
                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
    }
}