package ru.e2e4.shopmobile

import android.app.Application
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.di.components.*
import javax.inject.Singleton

class App : Application(), ComponentContract {

    @Singleton
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .bindContext(this)
            .build()
    }

    override fun getMainComponent(): MainComponent {
        return appComponent.getMainComponent()
    }

    override fun getCatalogComponent(): CatalogComponent {
        return getMainComponent().getCatalogComponent()
    }

    override  fun getSearchComponent(): SearchComponent {
        return getMainComponent().getSearchComponent()
    }
}