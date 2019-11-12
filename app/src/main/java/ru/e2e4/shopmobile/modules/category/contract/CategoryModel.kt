package ru.e2e4.shopmobile.modules.category.contract

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.category.data.CategoryNode

interface CategoryModel {
    fun getCategoryTree(): Single<CategoryNode>
}