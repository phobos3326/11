package com.example.cinematest.useCase

import com.example.cinematest.repository.RepositoryCinema
import java.util.Locale

class GetGenresUseCase(private val repositoryCinema: RepositoryCinema) {
    private var finalList:MutableList<String?> = mutableListOf()
    var listGenres = mutableListOf<String?>()
    suspend fun getFilmGenre(): List<String?> {



        repositoryCinema.getFilm().films?.forEach {



            it.genres?.let { it1 ->
                listGenres.addAll(it1)
            }
        }




        listGenres.forEach { it->
           finalList.add(it?.let { it1 -> capitalizeFirstLetter(it1) })

        }


        return  LinkedHashSet( finalList).toMutableList()
    }


    private fun capitalizeFirstLetter(word: String): String {
        return word.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

}