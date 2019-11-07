package ru.e2e4.shopmobile

import android.app.Application
import ru.e2e4.shopmobile.di.components.AppComponent
import ru.e2e4.shopmobile.di.components.DaggerAppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .bindContext(this)
            .build()
    }
}