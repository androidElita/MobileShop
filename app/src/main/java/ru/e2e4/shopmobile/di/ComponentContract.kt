package ru.e2e4.shopmobile.di

import ru.e2e4.shopmobile.di.components.CategoryComponent
import ru.e2e4.shopmobile.di.components.MainComponent
import ru.e2e4.shopmobile.di.components.SearchComponent

interface ComponentContract {

    fun getMainComponent(): MainComponent

    fun getCategoryComponent(): CategoryComponent

    fun getSearchComponent(): SearchComponent
}