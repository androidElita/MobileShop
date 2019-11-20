package ru.e2e4.shopmobile.modules.search.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.e2e4.shopmobile.modules.search.contract.SearchModel
import ru.e2e4.shopmobile.modules.search.contract.SearchPresenter
import ru.e2e4.shopmobile.modules.search.contract.SearchView
import ru.e2e4.shopmobile.modules.search.data.Search
import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.modules.search.data.SearchResult
import ru.e2e4.shopmobile.mvp.RxAbstractPresenter
import ru.e2e4.shopmobile.utils.language.numbericalFormOfNoun
import ru.e2e4.shopmobile.utils.language.pluralDefinition
import ru.e2e4.shopmobile.utils.resource.ResourceManager
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(
    private val model: SearchModel,
    private val resource: ResourceManager
) : SearchPresenter, RxAbstractPresenter<SearchView>() {

    override fun confirmInput(inputText: String) {
        val data = handleText(inputText)
        if (data.isEmpty()) return
        model.addSearchHistory(data)
    }

    private fun search(inputText: String) {
        val data = handleText(inputText)
        if (data.isEmpty()) return
        val result = Search(count = 3, items = listOf(
            SearchResult("Ноутбуки"),
            SearchResult("Клавиатура для ноутбука"),
            SearchResult("Вентиляторы и системы охлаждения")
        ))
        getView().showSearchMessage(
            getCountSearchMessage(result.count)
        )
        getView().showSearchResult(result.items)
    }

    override fun inputtingText(inputText: String) {
        if (inputText.isEmpty()){
            loadSearchHistory()
            getView().hideCleanSearch()
        } else {
            getView().showCleanSearch()
            search(inputText)
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
                        getView().showSearchHistory(list)
                    }
                }, {
                    emptyHistory()
                })
        )
    }

    override fun selectHistoryItem(item: SearchItem) {
        getView().setInputSearchText(item.text)
    }

    override fun selectSearchItem(item: SearchItem) {

    }

    override fun cleanInputSearch() {
        getView().cleanInputSearch()
    }

    override fun cleanHistory() {
        model.cleanHistory()
        loadSearchHistory()
    }

    private fun handleText(inputText: String): String {
        return inputText.toLowerCase() //TODO С точки зрения локализации это плохо (https://developer.android.com/reference/java/util/Locale.html#default_locale)
            .trim()
    }

    private fun emptySearch() {
        getView().showSearchResult(listOf())
        getView().showSearchMessage(resource.searchEmpty)
    }

    private fun emptyHistory() {
        getView().showSearchResult(listOf())
        getView().showSearchMessage(resource.searchHistoryEmpty)
    }

    private fun getCountSearchMessage(count: Int): String {
        val foundText = pluralDefinition(count, resource.found[0], resource.found[1])
        val goodsText = numbericalFormOfNoun(count, resource.goods[0], resource.goods[1], resource.goods[2])
        return "$foundText $count $goodsText"
    }
}