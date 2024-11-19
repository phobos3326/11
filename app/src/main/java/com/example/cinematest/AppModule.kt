package com.example.cinematest

import com.example.cinematest.ui.fragments.FilmViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {


    viewModelOf(::FilmViewModel)
}
