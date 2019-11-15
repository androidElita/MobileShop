package ru.e2e4.shopmobile.modules.catalog.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryView
import ru.e2e4.shopmobile.mvp.RxAbstractPresenter
import javax.inject.Inject

class CategoryPresenterImpl @Inject constructor(
    private val model: CategoryModel
)  : CategoryPresenter, RxAbstractPresenter<CategoryView>() {

    override fun attachView(view: CategoryView) {
        super.attachView(view)
    }

    override fun loadCategoryTree() {
        addDisposable(
            model.loadCategoryTree()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    getView().showCategory(it)
                }, {
                    getView().showError()
                })
        )
    }
}