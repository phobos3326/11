package com.example.cinematest.useCase
import com.example.cinematest.repository.RepositoryCinema
import com.example.cinematest.entity.ModelCinema

class UseCaseFilm(private val repositoryCinema: RepositoryCinema) {
    suspend fun execFilms(): List<ModelCinema.Film> {
       return repositoryCinema.getFilm().films
    }
}