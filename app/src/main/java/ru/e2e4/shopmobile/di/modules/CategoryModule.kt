package ru.e2e4.shopmobile.di.modules

import dagger.Module
import dagger.Provides
import ru.e2e4.shopmobile.di.scopes.CategoryScope
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import ru.e2e4.shopmobile.modules.catalog.model.CategoryModelImpl
import ru.e2e4.shopmobile.modules.catalog.presenter.CategoryPresenterImpl
import ru.e2e4.shopmobile.retrofit.NetworkService

@Module
class CategoryModule {

    @Provides
    @CategoryScope
    fun provideCategoryRestService(service: NetworkService): CategoryRestService {
        return service.create(CategoryRestService::class.java)
    }

    @Provides
    @CategoryScope
    fun provideCategoryPresenter(presenter: CategoryPresenterImpl): CategoryPresenter = presenter

    @Provides
    @CategoryScope
    fun provideCategoryModel(model: CategoryModelImpl): CategoryModel = model
}