package com.example.cinematest.UseCase

import com.example.cinematest.Repositoy.RepositotyCinema

class UseCaseFilm(private val repositotyCinema: RepositotyCinema) {
    suspend fun execFilms(){
        repositotyCinema.getFilm()
    }
}