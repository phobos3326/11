package com.example.cinematest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}
