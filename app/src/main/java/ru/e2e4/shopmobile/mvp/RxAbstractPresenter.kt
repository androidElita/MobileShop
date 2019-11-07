package ru.e2e4.shopmobile.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxAbstractPresenter<V : BaseView> : AbstractPresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun detachView() {
        compositeDisposable.dispose()
        super.detachView()
    }
}