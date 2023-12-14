package com.d121211065.tmdb

import android.app.Application
import com.d121211065.tmdb.data.AppContainer
import com.d121211065.tmdb.data.DefaultAppContainer

class MyApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}