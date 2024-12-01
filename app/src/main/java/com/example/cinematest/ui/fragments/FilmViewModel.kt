package com.example.cinematest.ui.fragments

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.repository.RepositoryCinema
import com.example.cinematest.useCase.UseCaseFilm
import com.example.cinematest.useCase.getGenresUseCase
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(
    private val useCaseFilm: UseCaseFilm,
    private val getGenresUseCase: getGenresUseCase,
    private val repositoryCinema: RepositoryCinema,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<State>(State.ColdStart)
    val state = _state.asStateFlow()

    private var _filmId = MutableStateFlow<ModelCinema.Film?>(null)
    val filmId: StateFlow<ModelCinema.Film?> = _filmId


    private var _films = MutableStateFlow<List<ModelCinema.Film>>(emptyList())
    var films = _films.asStateFlow()

    private var _genre = MutableStateFlow<List<String?>>(emptyList())
    var genre = _genre.asStateFlow()

    private var genre__: String? = null

    init {

        viewModelScope.launch {
            /*getFilm()
            getFilmGenre()
            filterFilm()*/
            start()

           // repositoryCinema.getResponse()
        }
    }


    fun start() {
        isNetworkAvailable(getApplication<Application>().applicationContext)
        viewModelScope.launch() {
            try {
                _state.value = State.Wait
                val response = repositoryCinema.getResponse()
                Log.e("TAG", "$response, ${_state.value}")
                getFilm()
                getFilmGenre()
                filterFilm()

                _state.value = State.Completed
                Log.e("TAG", "$response, ${_state.value}")
            } catch (e: Exception) {
                _state.value = State.Error
            }
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


    private var isConnect = true
    private fun isNetworkAvailable(context: Context) {
        viewModelScope.launch {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                _state.value = State.ColdStart
                Log.e("TAG", "$isConnect, ${_state.value}")
                isConnect = true
            } else {
                _state.value = State.Error
                Log.e("TAG", "$isConnect, ${_state.value}")
                isConnect = false
            }
        }
    }




}
