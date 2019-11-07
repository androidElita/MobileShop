package ru.e2e4.shopmobile.mvp

abstract class AbstractPresenter<V : BaseView> : BasePresenter<V> {

    private var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    fun getView(): V {
        return mView ?: throw IllegalStateException(ATTACH_VIEW_ERROR)
    }

    companion object {
        const val ATTACH_VIEW_ERROR = "View must be attached to Presenter!"
    }
}