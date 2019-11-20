package ru.e2e4.shopmobile.di.modules.catalog

import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.di.scopes.CategoriesScope
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import ru.e2e4.shopmobile.modules.catalog.model.CategoriesModelImpl
import ru.e2e4.shopmobile.modules.catalog.presenter.CategoriesPresenterImpl
import ru.e2e4.shopmobile.retrofit.NetworkService

@Module
class CategoriesModule {

    @Provides
    @CategoriesScope
    fun provideCategoryRestService(service: NetworkService): CategoryRestService {
        return service.create(CategoryRestService::class.java)
    }

    @Provides
    @CategoriesScope
    fun provideCategoryPresenter(presenter: CategoriesPresenterImpl): CategoryPresenter {
        return presenter
    }

    @Provides
    @CategoriesScope
    fun provideCategoryModel(model: CategoriesModelImpl): CategoryModel {
        return model
    }
}