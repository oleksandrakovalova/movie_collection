package com.okproject.moviecollection

import android.app.Application
import com.okproject.moviecollection.data.movie.movies
import com.okproject.moviecollection.data.networking.networking
import com.okproject.moviecollection.ui.screens.uiScreens
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieCollectionApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieCollectionApplication)
            androidLogger()
            modules(networking, movies, uiScreens)
        }
    }
}