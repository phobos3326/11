package com.example.cinematest.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.useCase.UseCaseFilm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(private val useCaseFilm: UseCaseFilm) : ViewModel() {
    // TODO: Implement the ViewModel

    private var _films = MutableStateFlow<List<ModelCinema.Film>>(emptyList())
    var films = _films.asStateFlow()

    init {

        viewModelScope.launch {
            getFilm()
        }
    }

    suspend fun getFilm() {
        _films.value=  useCaseFilm.execFilms()
    }

}
