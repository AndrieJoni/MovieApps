package stickearn.movie.stickearnmovieapps.view.moviedetails.presentation

import android.util.Base64
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import stickearn.movie.stickearnmovieapps.data.MovieData
import stickearn.movie.stickearnmovieapps.data.MovieReviewData
import stickearn.movie.stickearnmovieapps.repository.MovieRepository
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.moviedetails.presentation.reviews.ReviewsMovieDataSourceFactory
import stickearn.movie.stickearnmovieapps.view.movieFavorite.data.FavoriteMovieEntity
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class DetailMovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieData: MovieData? = null
    val showMovieData = MutableLiveData<MovieData>()
    val saveFavoriteMovieEvent = SingleLiveEvent<Boolean>()
    val changeFavoriteIconColorEvent = SingleLiveEvent<Boolean>()
    val shareLinkEvent = SingleLiveEvent<String>()

    private var isFavorite = false

    var reviewsMovieDataSourceFactory: ReviewsMovieDataSourceFactory? = null

    init {
        movieData = savedStateHandle.get<MovieData>(DetailMovieActivity.MOVIE_DATA) as MovieData
        getMovieData()

        tes()
    }


    private fun tes() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
              Log.d("coba",decrypt(""))
            }
        }
    }

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun initializeReviewsMovieLiveData(): LiveData<PagedList<MovieReviewData>> {

        reviewsMovieDataSourceFactory = ReviewsMovieDataSourceFactory(
            movieData?.id.toString(),
            movieRepository,
            viewModelScope
        )

        return LivePagedListBuilder(
            reviewsMovieDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    private fun getMovieData() {

        showMovieData.value = movieData

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

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun shareIconClicked() {
        shareLinkEvent.value = movieData?.title.toString()
    }

    private fun mappingToEntity(movieData: MovieData): FavoriteMovieEntity {

        return FavoriteMovieEntity(
            movieData.id,
            movieData.title,
            movieData.releaseDate,
            movieData.overview,
            movieData.posterPath
        )
    }


    private fun decrypt(qrValue: String): String {

        val data = qrValue.split(Regex("--"), 0)
        val qrData = Base64.decode("qikVACi3G1UMeg+EcODYClYmqWY3LrnulFrnFRJdvRUI7YpIxZ56Nzc12YWyNTVqC0WdekDfnIX4LuNIbdsc2CQ8GrxeVq+jfqYH8HuHJoaVAXk0tfWOSQxYqLfBAbQB2wFtU/q1ARgCI7yW9UR+NdQJP0D+ecaoOSgmjlfSgXnjTwCXh8fHDFXXhhJtyI9Kg2GQAg7Ca25kkmokutFsza4SMU+lSCPf7DCsOdkroEAMDSHINds34RiMyVorRYaui+/XseI/oRyOvwlZkLW+toAikDRs2WOGyOZ1fVSAFG8=", Base64.DEFAULT)
        val ivKey = Base64.decode("jSpho6Gxhe3WXldFZp9rJA==", Base64.DEFAULT)

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        val ivSpec = IvParameterSpec(ivKey)

        cipher.init(Cipher.DECRYPT_MODE, createKey(), ivSpec)

        return String(cipher.doFinal(qrData))
    }

    private fun createKey(): SecretKeySpec {

        val pbKeySpec = PBEKeySpec(
            "Us-jEssdSyPsqQ3t1fsa".toCharArray(),
            "GZZgpz9QeZAH9fETFzWc".toByteArray(charset = Charsets.UTF_8),
            65536,
            256
        )

        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded

        return SecretKeySpec(keyBytes, "AES")
    }

}