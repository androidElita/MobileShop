package ru.e2e4.shopmobile.modules.catalog.contract

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode
import ru.e2e4.shopmobile.mvp.BasePresenter
import ru.e2e4.shopmobile.mvp.BaseView

interface CategoriesContract {
    interface CategoryModel {
        fun loadCategoryTree(): Single<CategoriesNode>
    }

    interface CategoryView : BaseView {
        fun showCategory(categories: CategoriesNode)
        fun showError()
    }

    interface CategoryPresenter : BasePresenter<CategoryView> {
        fun loadCategoryTree()
    }
}