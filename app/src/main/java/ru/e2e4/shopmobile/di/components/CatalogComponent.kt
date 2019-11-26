package ru.e2e4.shopmobile.di.components

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.CatalogModule
import ru.e2e4.shopmobile.di.scopes.CatalogScope
import ru.e2e4.shopmobile.modules.catalog.view.CategoriesFragment

@Subcomponent(modules = [CatalogModule::class])
@CatalogScope
interface CatalogComponent {
    fun inject(view: CategoriesFragment)
}