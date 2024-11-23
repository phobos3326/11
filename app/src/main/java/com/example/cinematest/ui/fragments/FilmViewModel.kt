package com.example.cinematest.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.useCase.UseCaseFilm
import kotlinx.coroutines.launch

class FilmViewModel(private val useCaseFilm: UseCaseFilm) : ViewModel() {
    // TODO: Implement the ViewModel


    init {

        viewModelScope.launch {
            getFilm()
        }



    }

    suspend fun getFilm(): List<ModelCinema.Film?>? {

        return useCaseFilm.execFilms()

    }



}
