package ru.e2e4.shopmobile.di.components.catalog

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.catalog.CategoriesModule
import ru.e2e4.shopmobile.di.scopes.CategoriesScope
import ru.e2e4.shopmobile.modules.catalog.view.CategoriesFragment

@Subcomponent(modules = [CategoriesModule::class])
@CategoriesScope
interface CategoriesComponent {
    fun inject(view: CategoriesFragment)
}