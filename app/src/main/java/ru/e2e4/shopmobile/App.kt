package ru.e2e4.shopmobile

import android.app.Application
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.di.components.AppComponent
import ru.e2e4.shopmobile.di.components.DaggerAppComponent
import ru.e2e4.shopmobile.di.components.MainComponent
import ru.e2e4.shopmobile.di.components.SearchComponent
import ru.e2e4.shopmobile.di.components.catalog.CategoriesComponent
import ru.e2e4.shopmobile.di.components.catalog.SubcategoriesComponent
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

    override fun getCategoriesComponent(): CategoriesComponent {
        return getMainComponent().getCategoriesComponent()
    }

    override fun getSubcategoriesComponent(): SubcategoriesComponent {
        return getMainComponent().getSubcategoriesComponent()
    }

    override  fun getSearchComponent(): SearchComponent {
        return getMainComponent().getSearchComponent()
    }
}