package ru.e2e4.shopmobile.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.retrofit.NetworkService
import ru.e2e4.shopmobile.room.AppDatabase
import ru.e2e4.shopmobile.utils.resource.AndroidResourceManager
import ru.e2e4.shopmobile.utils.resource.ResourceManager
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(applicationContext: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

    @Singleton
    @Provides
    fun provideResourcesManager(applicationContext: Context): ResourceManager {
        return AndroidResourceManager(applicationContext.resources)
    }

    @Singleton
    @Provides
    fun provideDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            applicationContext.getString(R.string.nameDB)
        ).allowMainThreadQueries().build()
    }
}