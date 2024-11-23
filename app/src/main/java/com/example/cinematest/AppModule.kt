package com.example.cinematest


import com.example.cinematest.ui.fragments.FilmViewModel
import com.example.cinematest.useCase.UseCaseFilm
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


//viewModelOf(::FilmViewModel)

val appModule = module {

    viewModel { FilmViewModel(get()) }
    single { UseCaseFilm(get()) }
    /* single<RepositotyCinema> { RepositotyCinema() }*/
   // viewModelOf(::FilmViewModel)

    /*   scope<FilmFragment> {
           scoped { RepositotyCinema() }
       }*/
}


