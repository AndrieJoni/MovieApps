package stickearn.movie.stickearnmovieapps.view.movieFavorite.data

import stickearn.movie.stickearnmovieapps.view.movieFavorite.domain.FavoriteMovie

fun Collection<com.example.basedata.local.MovieEntity>.toFavoriteMovie(): List<FavoriteMovie> {

    return map {
        FavoriteMovie(
            id = it.id,
            name = it.title.toString(),
            releaseDate = it.releaseDate,
            overview = it.description.toString(),
            imageLink = it.imageUrl.toString()
        )
    }
}