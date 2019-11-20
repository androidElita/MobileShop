package ru.e2e4.shopmobile.modules.search.presenter

import io.reactivex.Single
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
import java.util.concurrent.TimeUnit
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

    override fun search(inputText: String) {
        val data = handleText(inputText)
        if (data.isEmpty()) {
            loadSearchHistory()
            return
        }
        addDisposable(
            Single.just( // TODO Заменить на запрос
                Search(count = 325342, items = listOf(
                    SearchResult(data),
                    SearchResult("Ноутбуки"),
                    SearchResult("Клавиатура для ноутбука"),
                    SearchResult("Вентиляторы и системы охлаждения")
            )))
                .delay(0, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { getView().showLoadingAction() }
                .doFinally { getView().hideLoadingAction() }
                .flatMap {
                    getView().showSearchMessage(getCountSearchMessage(it.count))
                    return@flatMap Single.just(it.items as List<SearchItem>)
                }
                .concatWith(model.getSearchHistory())
                .flatMapIterable { list -> list }
                .toList()
                .subscribe({
                    when {
                        getView().getInputSearchText().isEmpty() -> loadSearchHistory()
                        it.isNotEmpty() -> getView().showSearchResult(it)
                        else -> emptySearch()
                    }
                }, {
                    // TODO обрабатывать
                    emptySearch()
                })
        )
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
        // TODO обрабатывать
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
        return if (count > 0){
            val foundText = pluralDefinition(count, resource.found[0], resource.found[1])
            val goodsText = numbericalFormOfNoun(count, resource.goods[0], resource.goods[1], resource.goods[2])
            "$foundText $count $goodsText"
        } else resource.searchEmpty
    }
}