package ru.e2e4.shopmobile.modules.catalog.model

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import javax.inject.Inject

class CategoriesModelImpl @Inject constructor(
    private val service: CategoryRestService
) : CategoryModel {

    override fun loadCategoryTree(): Single<CategoriesNode> {
        return service.getCategoryTree(1)
    }
}