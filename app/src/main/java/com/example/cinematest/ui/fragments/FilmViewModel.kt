package com.example.cinematest.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.useCase.UseCaseFilm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(private val useCaseFilm: UseCaseFilm) : ViewModel() {
    // TODO: Implement the ViewModel



    private var _filmId = MutableStateFlow<ModelCinema.Film?>(null)
    val filmId: StateFlow<ModelCinema.Film?> = _filmId


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





    suspend fun getFilmId(id:Int){
        useCaseFilm.execFilms().forEach {  it->
            if (it.id == id){
                _filmId.value = it
            }
        }
    }

}
