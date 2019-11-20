package ru.e2e4.shopmobile.di.modules.catalog

import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.di.scopes.SubcategoriesScope
import ru.e2e4.shopmobile.modules.catalog.contract.SubcategoriesContract.SubcategoriesModel
import ru.e2e4.shopmobile.modules.catalog.contract.SubcategoriesContract.SubcategoriesPresenter
import ru.e2e4.shopmobile.modules.catalog.model.SubcategoriesModelImpl
import ru.e2e4.shopmobile.modules.catalog.presenter.SubcategoriesPresenterImpl

@Module
class SubcategoriesModule {

    @SubcategoriesScope
    @Provides
    fun provideSubcategoriesModel(model: SubcategoriesModelImpl): SubcategoriesModel {
        return model
    }

    @SubcategoriesScope
    @Provides
    fun provideSubcategoriesPresenter(presenter: SubcategoriesPresenterImpl): SubcategoriesPresenter {
        return presenter
    }
}