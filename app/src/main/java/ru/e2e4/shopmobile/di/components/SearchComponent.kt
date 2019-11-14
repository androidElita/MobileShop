package ru.e2e4.shopmobile.di.components

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.SearchModule
import ru.e2e4.shopmobile.di.scopes.SearchScope
import ru.e2e4.shopmobile.modules.search.view.SearchFragment

@Subcomponent(modules = [SearchModule::class])
@SearchScope
interface SearchComponent {
    fun inject(view: SearchFragment)
}