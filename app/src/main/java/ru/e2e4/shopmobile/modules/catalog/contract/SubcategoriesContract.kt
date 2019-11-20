package ru.e2e4.shopmobile.modules.catalog.contract

import ru.e2e4.shopmobile.mvp.BasePresenter
import ru.e2e4.shopmobile.mvp.BaseView

interface SubcategoriesContract {
    interface SubcategoriesModel {

    }

    interface SubcategoriesView : BaseView {

    }

    interface SubcategoriesPresenter : BasePresenter<SubcategoriesView> {

    }
}