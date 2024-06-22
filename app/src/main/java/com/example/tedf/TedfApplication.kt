package com.example.tedf

import android.app.Application
import com.example.tedf.data.AppContainer
import com.example.tedf.data.AppDataContainer

class TedfApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
