package ru.e2e4.shopmobile.modules.category.contract

import ru.e2e4.shopmobile.mvp.BasePresenter

interface CategoryPresenter: BasePresenter<CategoryView> {
    fun loadCategoryTree()
}