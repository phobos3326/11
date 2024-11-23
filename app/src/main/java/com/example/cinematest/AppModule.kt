package com.example.cinematest

import com.example.cinematest.repository.RepositoryCinema
import com.example.cinematest.ui.fragments.FilmViewModel
import com.example.cinematest.useCase.UseCaseFilm
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { RepositoryCinema() }
    single { UseCaseFilm(get()) }
    viewModel { FilmViewModel(get()) }
}


