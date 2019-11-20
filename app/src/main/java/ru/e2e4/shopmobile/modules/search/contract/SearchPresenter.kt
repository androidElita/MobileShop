package ru.e2e4.shopmobile.modules.search.contract

import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.mvp.BasePresenter

interface SearchPresenter : BasePresenter<SearchView> {

    /**
     * Поиск по введенным данным
     * @param inputText введенный текст
     */
    fun confirmInput(inputText: String)

    /**
     * Обработка ввод текста
     * @param inputText введенный текст
     */
    fun inputtingText(inputText: String)

    fun search(inputText: String)

    fun selectHistoryItem(item: SearchItem)
    fun selectSearchItem(item: SearchItem)

    fun loadSearchHistory()
    fun cleanHistory()
    fun cleanInputSearch()
}