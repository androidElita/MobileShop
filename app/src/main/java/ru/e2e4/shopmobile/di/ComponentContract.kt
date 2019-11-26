package ru.e2e4.shopmobile.di

import ru.e2e4.shopmobile.di.components.CatalogComponent
import ru.e2e4.shopmobile.di.components.MainComponent
import ru.e2e4.shopmobile.di.components.SearchComponent

interface ComponentContract {

    fun getMainComponent(): MainComponent

    fun getCatalogComponent(): CatalogComponent

    fun getSearchComponent(): SearchComponent
}