package ru.e2e4.shopmobile.modules.catalog.contract

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.catalog.data.CategoryNode

interface CategoryModel {
    fun loadCategoryTree(): Single<CategoryNode>
}