package com.example.moviefavorite

import androidx.paging.ItemKeyedDataSource
import com.example.commonui.MovieModel
import com.example.commonui.toMovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetFavoriteMovieListFromLocalUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteMovieDataSource constructor(
    private val getFavoriteMovieListFromLocalUseCase: GetFavoriteMovieListFromLocalUseCase,
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val scope: CoroutineScope
) : ItemKeyedDataSource<Int, MovieModel>() {

    override fun getKey(item: MovieModel) = item.id

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<MovieModel>
    ) {

        scope.launch {

            try {

                val data = withContext(Dispatchers.IO) {
                    getFavoriteMovieListFromLocalUseCase.invoke(
                        0,
                    )
                }

                if (data.isEmpty()) {
                    paginationStatus.value = PaginationStatus.Empty
                } else {
                    callback.onResult(data.map {
                        it.toMovieModel()
                    })
                }

            } catch (e: Exception) {
                paginationStatus.value = PaginationStatus.Error
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MovieModel>) {

        scope.launch {

            try {

                val data = withContext(Dispatchers.IO) {
                    getFavoriteMovieListFromLocalUseCase.invoke(
                        params.key,
                    )
                }

                callback.onResult(data.map { it.toMovieModel() })

            } catch (e: Exception) {
                paginationStatus.value = PaginationStatus.Error
                e.printStackTrace()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MovieModel>) {

    }
}

