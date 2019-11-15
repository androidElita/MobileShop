package ru.e2e4.shopmobile.modules.catalog.model

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.data.CategoryNode
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import javax.inject.Inject

class CategoryModelImpl @Inject constructor(
    private val service: CategoryRestService
) : CategoryModel {

    override fun loadCategoryTree(): Single<CategoryNode> {
        return service.getCategoryTree(1)
    }
}