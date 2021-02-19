package com.example.util

sealed class PaginationStatus {

    object Empty : PaginationStatus()
    object Error : PaginationStatus()
}

