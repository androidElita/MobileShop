package ru.e2e4.shopmobile.di.components

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.components.catalog.CategoriesComponent
import ru.e2e4.shopmobile.di.components.catalog.SubcategoriesComponent
import ru.e2e4.shopmobile.di.modules.MainModule
import ru.e2e4.shopmobile.di.scopes.MainScope

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun getCategoriesComponent(): CategoriesComponent
    fun getSubcategoriesComponent(): SubcategoriesComponent
    fun getSearchComponent(): SearchComponent
}