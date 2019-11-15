package ru.e2e4.shopmobile.modules.catalog.contract

import ru.e2e4.shopmobile.mvp.BasePresenter

interface CategoryPresenter: BasePresenter<CategoryView> {
    fun loadCategoryTree()
}