package stickearn.movie.stickearnmovieapps.view.movieFavorite.domain

interface FavoriteMovieRepository {

    suspend fun getAllMovie(): List<FavoriteMovie>
}