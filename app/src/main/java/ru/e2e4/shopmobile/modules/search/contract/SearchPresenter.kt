package ru.e2e4.shopmobile.modules.search.contract

import ru.e2e4.shopmobile.mvp.BasePresenter

interface SearchPresenter : BasePresenter<SearchView> {
    fun loadSearchHistory()
    fun addSearchHistory(text: String)
    fun cleanHistory()
}