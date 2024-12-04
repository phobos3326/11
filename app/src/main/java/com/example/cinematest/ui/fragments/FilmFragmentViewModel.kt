package com.example.cinematest.ui.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.repository.RepositoryCinema
import com.example.cinematest.useCase.ConnectivityUseCase
import com.example.cinematest.useCase.UseCaseFilm
import com.example.cinematest.useCase.GetGenresUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class FilmFragmentViewModel(
    private val useCaseFilm: UseCaseFilm,
    private val getGenresUseCase: GetGenresUseCase,
    private val repositoryCinema: RepositoryCinema,
    private val connectivityUseCase: ConnectivityUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<State>(State.ColdStart)
    val state = _state.asStateFlow()

    private var _filmId = MutableStateFlow<ModelCinema.Film?>(null)
    val filmId: StateFlow<ModelCinema.Film?> = _filmId


    private var _films = MutableStateFlow<List<ModelCinema.Film?>>(emptyList())
    var films = _films.asStateFlow()

    private var _genre = MutableStateFlow<List<String?>>(emptyList())
    var genre = _genre.asStateFlow()

    val isConnect = connectivityUseCase.isConnected as StateFlow<Boolean>


    private var genre__: String? = null

    init {
        viewModelScope.launch {
            start()
            isConnect
        }
    }


    fun start() {
        viewModelScope.launch {
            _state.value = State.Wait
            isConnect.collect { isOnline ->
                if(isOnline){
                    _state.value = State.Wait
                    runCatching {
                        repositoryCinema.getResponse().also { response ->
                            Log.e("TAG", "$response, ${_state.value}")
                            _state.value = State.Wait
                        }
                    }.fold(
                        onSuccess = { response ->
                            _state.value = State.Wait
                            getFilm()
                            getFilmGenre()
                            filterFilm()
                            Log.e("TAG", "$response, ${_state.value}")
                            _state.value = State.Completed

                        },
                        onFailure = {
                            _state.value = State.Error
                        }
                    )
                }else{
                    _state.value = State.Error
                }

            }
        }
    }


    suspend fun getFilm(): List<ModelCinema.Film?>? {
                useCaseFilm.execFilms().also {
            if (it != null) {
                _films.value = it
            }
        }
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
            val filteredList = filmsList?.filter {
                it?.genres!!.contains(genre__!!.lowercase(Locale.getDefault()))
            }
            if (filteredList != null) {
                _films.value = filteredList
            }
        } else {
            if (filmsList != null) {
                _films.value = filmsList
            }
        }
    }

    private suspend fun getFilmGenre() {
        _genre.value = getGenresUseCase.getFilmGenre()
    }





    suspend fun getFilmId(id: Int) {
        useCaseFilm.execFilms()?.forEach { it ->
            if (it?.id == id) {
                _filmId.value = it
            }
        }
    }
}


