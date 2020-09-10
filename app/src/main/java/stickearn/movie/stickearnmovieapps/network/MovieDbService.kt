package stickearn.movie.stickearnmovieapps.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import stickearn.movie.stickearnmovieapps.BuildConfig

interface MovieDbService {

    @GET("movie/popular?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getPopularMovies(
        @Query("page") page: String
    ): ListOfMoviesResponse

    @GET("movie/top_rated?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getTopRatedMovies(
        @Query("page") page: String
    ): ListOfMoviesResponse

    @GET("movie/now_playing?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getNowPlayingMovies(
        @Query("page") page: String
    ): ListOfMoviesResponse

    @GET("movie/{movie_id}/reviews?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: String,
        @Query("page") page: String
    ): MoviesReviewResponse

}