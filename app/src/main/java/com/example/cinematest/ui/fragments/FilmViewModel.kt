package com.example.cinematest.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.useCase.UseCaseFilm
import com.example.cinematest.useCase.getGenresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf

class FilmViewModel(
    private val useCaseFilm: UseCaseFilm,
    private val getGenresUseCase: getGenresUseCase
) : ViewModel() {


    private var _filmId = MutableStateFlow<ModelCinema.Film?>(null)
    val filmId: StateFlow<ModelCinema.Film?> = _filmId


    private var _films = MutableStateFlow<List<ModelCinema.Film>>(emptyList())
    var films = _films.asStateFlow()

    private var _genre = MutableStateFlow<List< String?>>(emptyList())
    var genre = _genre.asStateFlow()

    init {

        viewModelScope.launch {
            getFilm()
            getFilmGenre()

        }
    }

    suspend fun getFilm() {
        _films.value = useCaseFilm.execFilms()

    }


    private suspend fun getFilmGenre(){
       _genre.value= getGenresUseCase.getFilmGenre()
    }

/*    suspend fun getFilmGenre() {
        var listgenres = mutableListOf<String?>()
        useCaseFilm.execFilms().forEach {
            it.genres?.let { it1 ->
                listgenres.addAll(it1)
            }


        }
        //listgenres.distinct().toList()
        val distinct = LinkedHashSet(listgenres)
        println(distinct)

    }*/


    suspend fun getFilmId(id: Int) {
        useCaseFilm.execFilms().forEach { it ->
            if (it.id == id) {
                _filmId.value = it
            }
        }
    }

}
