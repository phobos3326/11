package com.example.cinematest.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.useCase.UseCaseFilm
import com.example.cinematest.useCase.getGenresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(
    private val useCaseFilm: UseCaseFilm,
    private val getGenresUseCase: getGenresUseCase
) : ViewModel() {


    private var _filmId = MutableStateFlow<ModelCinema.Film?>(null)
    val filmId: StateFlow<ModelCinema.Film?> = _filmId


    private var _films = MutableStateFlow<List<ModelCinema.Film>>(emptyList())
    var films = _films.asStateFlow()

    private var _genre = MutableStateFlow<List<String?>>(emptyList())
    var genre = _genre.asStateFlow()

    private var genre__: String? = null

    init {

        viewModelScope.launch {
            getFilm()
            getFilmGenre()
            filterFilm()
        }
    }

    private suspend fun getFilm(): List<ModelCinema.Film> {
        _films.value = useCaseFilm.execFilms()
        return useCaseFilm.execFilms()
    }


    fun getGenre(genre: String?) {
        genre__ = genre
        viewModelScope.launch {
            filterFilm()
        }

    }

    private suspend fun filterFilm() {
        val filmsList = getFilm()
        if (genre__ != null) {
            val filteredList = filmsList.filter {
                it.genres!!.contains(genre__)
            }
            _films.value = filteredList
        } else {
            _films.value = filmsList
        }
    }

    private suspend fun getFilmGenre() {
        _genre.value = getGenresUseCase.getFilmGenre()
    }


    suspend fun getFilmId(id: Int) {
        useCaseFilm.execFilms().forEach { it ->
            if (it.id == id) {
                _filmId.value = it
            }
        }
    }

}
