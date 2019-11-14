package ru.e2e4.shopmobile.modules.category.model

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.category.contract.CategoryModel
import ru.e2e4.shopmobile.modules.category.data.CategoryNode
import ru.e2e4.shopmobile.modules.category.data.CategoryRestService
import javax.inject.Inject

class CategoryModelImpl @Inject constructor(
    private val service: CategoryRestService
) : CategoryModel {

    override fun loadCategoryTree(): Single<CategoryNode> {
        return service.getCategoryTree(1)
    }
}