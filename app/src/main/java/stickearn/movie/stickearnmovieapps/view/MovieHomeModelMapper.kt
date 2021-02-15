package stickearn.movie.stickearnmovieapps.view

import com.movie.domain.entity.Movie
import stickearn.movie.stickearnmovieapps.view.movieHome.MovieHomeModel

fun Movie.toMovieHomeModel(): MovieHomeModel {
    return MovieHomeModel(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterImage = posterPath,
        backdropImage = backdropPath
    )
}