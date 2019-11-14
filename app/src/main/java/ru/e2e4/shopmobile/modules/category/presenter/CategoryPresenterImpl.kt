package ru.e2e4.shopmobile.modules.category.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.e2e4.shopmobile.modules.category.contract.CategoryModel
import ru.e2e4.shopmobile.modules.category.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.category.contract.CategoryView
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