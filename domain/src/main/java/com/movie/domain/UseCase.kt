package com.movie.domain

interface UseCase<R> {
    suspend operator fun invoke(): R
}