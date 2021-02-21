package com.example.home.popular

import androidx.paging.PageKeyedDataSource
import com.example.commonui.MovieModel
import com.example.commonui.toMovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetPopularMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesDataSource(
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val scope: CoroutineScope

) : PageKeyedDataSource<Int, MovieModel>() {

    private suspend fun getPopularMovieData(page: String) =
        getPopularMovieListUseCase.invoke(page)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {

        scope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    getPopularMovieData("1")
                }

                if (response.isEmpty()) {
                    paginationStatus.postValue(PaginationStatus.Empty)
                } else {
                    callback.onResult(response.map { it.toMovieModel() }, null, 2)
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

                val response = getPopularMovieData(params.key.toString())

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