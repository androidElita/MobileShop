package ru.e2e4.shopmobile.di.modules

import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.di.scopes.CatalogScope
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import ru.e2e4.shopmobile.modules.catalog.model.CategoriesModelImpl
import ru.e2e4.shopmobile.modules.catalog.presenter.CategoriesPresenterImpl
import ru.e2e4.shopmobile.retrofit.NetworkService

@Module
class CatalogModule {

    @Provides
    @CatalogScope
    fun provideCategoryRestService(service: NetworkService): CategoryRestService {
        return service.create(CategoryRestService::class.java)
    }

    @Provides
    @CatalogScope
    fun provideCategoryPresenter(presenter: CategoriesPresenterImpl): CategoryPresenter {
        return presenter
    }

    @Provides
    @CatalogScope
    fun provideCategoryModel(model: CategoriesModelImpl): CategoryModel {
        return model
    }
}