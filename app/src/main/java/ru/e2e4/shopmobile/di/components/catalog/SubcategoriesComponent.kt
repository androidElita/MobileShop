package ru.e2e4.shopmobile.di.components.catalog

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.catalog.SubcategoriesModule
import ru.e2e4.shopmobile.di.scopes.SubcategoriesScope
import ru.e2e4.shopmobile.modules.catalog.view.SubcategoriesFragment

@SubcategoriesScope
@Subcomponent(modules = [SubcategoriesModule::class])
interface SubcategoriesComponent {
    fun inject(fragment: SubcategoriesFragment)
}