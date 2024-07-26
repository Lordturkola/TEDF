package com.example.tedf_this_is_da_one

import android.app.Application
import com.example.tedf_this_is_da_one.data.AppContainer

class TedfApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}