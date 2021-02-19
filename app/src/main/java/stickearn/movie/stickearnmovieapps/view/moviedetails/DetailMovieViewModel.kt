package stickearn.movie.stickearnmovieapps.view.moviedetails

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.home.MovieHomeModel
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetMovieReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import stickearn.movie.stickearnmovieapps.repository.MovieRepository
import stickearn.movie.stickearnmovieapps.view.moviedetails.adapter.ReviewsMovieDataSourceFactory
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieData: MovieHomeModel? = null
    val showMovieData = MutableLiveData<MovieHomeModel>()
    val saveFavoriteMovieEvent = SingleLiveEvent<Boolean>()
    val changeFavoriteIconColorEvent = SingleLiveEvent<Boolean>()
    val shareLinkEvent = SingleLiveEvent<String>()

    private var isFavorite = false

    var reviewsMovieDataSourceFactory: ReviewsMovieDataSourceFactory? = null

    init {
        movieData = savedStateHandle.get<MovieHomeModel>(DetailMovieActivity.MOVIE_DATA)
        getMovieData()
    }

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun initializeReviewsMovieLiveData(): LiveData<PagedList<MovieReviewModel>> {

        reviewsMovieDataSourceFactory = ReviewsMovieDataSourceFactory(
            movieData?.id.toString(),
            getMovieReviewsUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            reviewsMovieDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    private fun getMovieData() {

        if (movieData != null) showMovieData.value = movieData!!

        viewModelScope.launch(Dispatchers.IO) {

            try {

                isFavorite = movieRepository.findMovieById(movieData?.id!!).isNotEmpty()

                withContext(Dispatchers.Main) {
                    changeFavoriteIconColorEvent.value = isFavorite
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun favoriteIconClicked() {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                if (movieData != null) {

                    if (!isFavorite) {
                        isFavorite = true
                        movieRepository.insertMovies(mappingToEntity(movieData!!))
                    } else {
                        isFavorite = false
                        movieRepository.deleteMovies(mappingToEntity(movieData!!))
                    }

                    withContext(Dispatchers.Main) {
                        saveFavoriteMovieEvent.value = isFavorite
                        changeFavoriteIconColorEvent.value = isFavorite
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun shareIconClicked() {
        shareLinkEvent.value = movieData?.title.toString()
    }

    private fun mappingToEntity(movieData: MovieHomeModel): com.example.basedata.local.FavoriteMovieEntity {

        return com.example.basedata.local.FavoriteMovieEntity(
            movieData.id,
            movieData.title,
            movieData.releaseDate,
            movieData.overview,
            movieData.posterImage
        )
    }
}