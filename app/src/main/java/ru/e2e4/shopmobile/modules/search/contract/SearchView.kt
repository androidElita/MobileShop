package ru.e2e4.shopmobile.modules.search.contract

import ru.e2e4.shopmobile.mvp.BaseView
import ru.e2e4.shopmobile.room.search.SearchHistory

interface SearchView : BaseView {
    fun showSearchHistory(list: List<SearchHistory>)
    fun hideSearchHistory()
    fun showEmptyHistoryMessage()
    fun hideEmptyHistoryMessage()
    fun cleanInputSearch()
    fun hideCleanSearch()
    fun showCleanSearch()
}