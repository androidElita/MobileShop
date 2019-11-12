package ru.e2e4.shopmobile.di.components

import dagger.Subcomponent
import ru.e2e4.shopmobile.di.modules.MainModule
import ru.e2e4.shopmobile.di.scopes.MainScope

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun getCategoryComponent(): CategoryComponent
}