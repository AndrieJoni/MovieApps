package stickearn.movie.stickearnmovieapps.view.movieFavorite.domain

class GetAllMovieUseCase(private val favoriteMovieRepository: FavoriteMovieRepository) {

    suspend operator fun invoke() = favoriteMovieRepository.getAllMovie()
}