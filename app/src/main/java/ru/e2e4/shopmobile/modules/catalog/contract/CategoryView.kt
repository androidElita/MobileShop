package ru.e2e4.shopmobile.modules.catalog.contract

import ru.e2e4.shopmobile.modules.catalog.data.CategoryNode
import ru.e2e4.shopmobile.mvp.BaseView

interface CategoryView : BaseView {
    fun showCategory(categories: CategoryNode)
    fun showError()
}