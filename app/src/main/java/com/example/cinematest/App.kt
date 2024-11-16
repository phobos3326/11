package com.example.cinematest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    /*  lateinit var db: ItemDataBase
      override fun onCreate() {
          super.onCreate()

          db = Room.databaseBuilder(
              applicationContext,
              ItemDataBase::class.java,
              "db"
          ).build()
      }*/



}