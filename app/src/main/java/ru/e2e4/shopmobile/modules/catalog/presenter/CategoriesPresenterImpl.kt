package ru.e2e4.shopmobile.modules.catalog.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.*
import ru.e2e4.shopmobile.mvp.RxAbstractPresenter
import javax.inject.Inject

class CategoriesPresenterImpl
@Inject constructor(
    private val model: CategoryModel
) : CategoryPresenter, RxAbstractPresenter<CategoryView>() {

    override fun loadCategoriesTree() {
        addDisposable(
            model.loadCategoriesTree()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    getView().showCategories(it)
                }, {
                    getView().showError()
                })
        )
    }
}