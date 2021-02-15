package com.movie.domain

interface UseCaseWithParam<P, R> {
    suspend fun invoke(parameter: P): R
}