package ru.e2e4.shopmobile.modules.search.contract

import ru.e2e4.shopmobile.mvp.BasePresenter

interface SearchPresenter : BasePresenter<SearchView> {

    /**
     * Поиск по введенным данным
     * @param inputText введенный текст
     */
    fun search(inputText: String)

    /**
     * Обработка ввод текста
     * @param inputText введенный текст
     */
    fun inputtingText(inputText: String)

    fun loadSearchHistory()
    fun cleanHistory()
    fun cleanInputSearch()
}