package com.example.cinematest.ui.fragments

sealed class State{
    object ColdStart:State()
    object Completed:State()
    object Wait:State()
    object Error:State()
}