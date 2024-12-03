package com.example.cinematest

import com.example.cinematest.repository.RepositoryCinema
import com.example.cinematest.ui.fragments.FilmFragmentViewModel
import com.example.cinematest.ui.fragments.ItemFragmentViewModel

import com.example.cinematest.useCase.ConnectivityUseCase
import com.example.cinematest.useCase.UseCaseFilm
import com.example.cinematest.useCase.GetGenresUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { RepositoryCinema() }
    single { UseCaseFilm(get()) }
    single { GetGenresUseCase(get()) }
    single { ConnectivityUseCase(get()) }
    viewModel { FilmFragmentViewModel(get(), get(), get(),  get(), get()) }
    viewModel { ItemFragmentViewModel(get(), get(), get()) }
}


