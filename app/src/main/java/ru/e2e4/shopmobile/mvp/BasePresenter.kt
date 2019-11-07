package ru.e2e4.shopmobile.mvp

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}