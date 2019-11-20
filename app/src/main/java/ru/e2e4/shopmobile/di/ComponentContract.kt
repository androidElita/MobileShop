package ru.e2e4.shopmobile.di

import ru.e2e4.shopmobile.di.components.MainComponent
import ru.e2e4.shopmobile.di.components.SearchComponent
import ru.e2e4.shopmobile.di.components.catalog.CategoriesComponent
import ru.e2e4.shopmobile.di.components.catalog.SubcategoriesComponent

interface ComponentContract {

    fun getMainComponent(): MainComponent

    fun getCategoriesComponent(): CategoriesComponent

    fun getSubcategoriesComponent(): SubcategoriesComponent

    fun getSearchComponent(): SearchComponent
}