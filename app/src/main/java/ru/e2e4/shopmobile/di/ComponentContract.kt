package ru.e2e4.shopmobile.di

import ru.e2e4.shopmobile.di.components.CategoryComponent
import ru.e2e4.shopmobile.di.components.MainComponent

interface ComponentContract {

    fun getMainComponent(): MainComponent

    fun getCategoryComponent(): CategoryComponent
}