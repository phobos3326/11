package com.example.cinematest

import com.example.cinematest.Repositoy.RepositotyCinema
import com.example.cinematest.ui.fragments.FilmFragment
import com.example.cinematest.ui.fragments.FilmViewModel

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module





    //viewModelOf(::FilmViewModel)

    val appModule = module {

        viewModelOf(::FilmViewModel)
        single<RepositotyCinema> { RepositotyCinema() }

        scope<FilmFragment> {
            scoped { RepositotyCinema() }
        }
    }


