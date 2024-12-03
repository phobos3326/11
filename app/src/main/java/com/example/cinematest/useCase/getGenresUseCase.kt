package com.example.cinematest.useCase

import com.example.cinematest.repository.RepositoryCinema
import java.util.Locale

class getGenresUseCase(private val repositoryCinema: RepositoryCinema) {
    private val finalList = mutableListOf<String?>(null)
    suspend fun getFilmGenre(): List<String?> {
        val listGenres = mutableListOf<String?>()
        repositoryCinema.getFilm().films?.forEach {
            it.genres?.let { it1 ->
                listGenres.addAll(it1)
            }
        }

        val list = LinkedHashSet(listGenres).toMutableList()
        for (i in 0..<list.size) {
            list[i]?.let { capitalizeFirstLetter(it) }?.let { finalList.add(it) }
        }
        if (finalList.isNotEmpty()) {
            finalList.removeAt(0)
        }
        return finalList
    }


    private fun capitalizeFirstLetter(word: String): String {
        return word.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

}