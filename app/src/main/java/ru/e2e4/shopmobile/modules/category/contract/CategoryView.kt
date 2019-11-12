package ru.e2e4.shopmobile.modules.category.contract

import ru.e2e4.shopmobile.modules.category.data.CategoryNode
import ru.e2e4.shopmobile.mvp.BaseView

interface CategoryView : BaseView {
    fun showCategory(categories: CategoryNode)
    fun showError()
}