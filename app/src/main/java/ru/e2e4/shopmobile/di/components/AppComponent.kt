package ru.e2e4.shopmobile.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.e2e4.shopmobile.di.modules.AppModule
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}