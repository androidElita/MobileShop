package ru.e2e4.shopmobile.modules.search.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.e2e4.shopmobile.modules.search.contract.SearchModel
import ru.e2e4.shopmobile.modules.search.contract.SearchPresenter
import ru.e2e4.shopmobile.modules.search.contract.SearchView
import ru.e2e4.shopmobile.mvp.RxAbstractPresenter
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(
    val model: SearchModel
) : SearchPresenter, RxAbstractPresenter<SearchView>() {

    override fun loadSearchHistory() {
        addDisposable(
            model.getSearchHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({list ->
                    if(list.isEmpty()) {
                        emptyHistory()
                    } else {
                        getView().showSearchHistory(list)
                    }
                }, {
                    emptyHistory()
                })
        )
    }

    private fun emptyHistory() {
        getView().showEmptyHistoryMessage()
       // getView().showSearchHistory(listOf())
    }

    override fun addSearchHistory(text: String) {
        model.addSearchHistory(text)
    }

    override fun cleanHistory() {
        model.cleanHistory()
    }
}