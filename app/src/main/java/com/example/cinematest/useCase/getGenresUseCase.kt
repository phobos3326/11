package com.example.cinematest.useCase

import com.example.cinematest.repository.RepositoryCinema

class getGenresUseCase(private val repositoryCinema: RepositoryCinema) {

    suspend fun getFilmGenre(): List<String?> {
        val listGenres = mutableListOf<String?>()
        repositoryCinema.getFilm().films?.forEach {
            it.genres?.let { it1 ->
                listGenres.addAll(it1)
            }
        }
        return LinkedHashSet(listGenres).toList()
    }
}