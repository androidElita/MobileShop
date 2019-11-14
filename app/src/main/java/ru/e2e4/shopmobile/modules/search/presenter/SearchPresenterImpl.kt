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

    override fun search(inputText: String) {
        val data = inputText.toLowerCase() //TODO С точки зрения локализации это плохо (https://developer.android.com/reference/java/util/Locale.html#default_locale)
            .trim()
        if (data.isEmpty()) return
        getView().cleanInputSearch()
        model.addSearchHistory(data)
        loadSearchHistory()
    }

    override fun inputtingText(inputText: String) {
        if (inputText.isEmpty()){
            getView().hideCleanSearch()
        } else {
            getView().showCleanSearch()
        }
    }

    override fun loadSearchHistory() {
        addDisposable(
            model.getSearchHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({list ->
                    if (list.isEmpty()) {
                        emptyHistory()
                    } else {
                        getView().hideEmptyHistoryMessage()
                        getView().showSearchHistory(list)
                    }
                }, {
                    emptyHistory()
                })
        )
    }

    override fun cleanInputSearch() {
        getView().cleanInputSearch()
    }

    private fun emptyHistory() {
        getView().hideSearchHistory()
        getView().showEmptyHistoryMessage()
    }

    override fun cleanHistory() {
        model.cleanHistory()
        loadSearchHistory()
    }
}