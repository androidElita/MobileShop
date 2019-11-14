package ru.e2e4.shopmobile.di.modules

import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.di.scopes.SearchScope
import ru.e2e4.shopmobile.modules.search.contract.SearchModel
import ru.e2e4.shopmobile.modules.search.contract.SearchPresenter
import ru.e2e4.shopmobile.modules.search.model.SearchModelImpl
import ru.e2e4.shopmobile.modules.search.presenter.SearchPresenterImpl
import ru.e2e4.shopmobile.room.AppDatabase
import ru.e2e4.shopmobile.room.search.SearchHistoryDao

@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideSearchPresenter(presenter: SearchPresenterImpl): SearchPresenter = presenter

    @Provides
    @SearchScope
    fun provideSearchModel(model: SearchModelImpl): SearchModel = model

    @Provides
    @SearchScope
    fun provideSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao =
        appDatabase.getSearchHistoryDao()
}