package ru.e2e4.shopmobile.di.components

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.CategoryModule
import ru.e2e4.shopmobile.di.scopes.CategoryScope
import ru.e2e4.shopmobile.modules.catalog.view.CategoryFragment

@Subcomponent(modules = [CategoryModule::class])
@CategoryScope
interface CategoryComponent {
    fun inject(view: CategoryFragment)
}