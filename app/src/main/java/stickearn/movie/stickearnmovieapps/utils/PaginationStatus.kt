package stickearn.movie.stickearnmovieapps.utils

sealed class PaginationStatus {

    object Empty : PaginationStatus()
    object Error : PaginationStatus()
}

