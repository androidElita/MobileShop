package ru.e2e4.shopmobile.modules.search.contract

import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.mvp.BaseView

interface SearchView : BaseView {
    fun showSearchResult(list: List<SearchItem>)
    fun showSearchHistory(list: List<SearchItem>)
    fun showSearchMessage(message: String)
    fun cleanInputSearch()
    fun setInputSearchText(text: String)
    fun getInputSearchText(): String
    fun showCleanSearch()
    fun hideCleanSearch()
    fun showCategorySwitch()
    fun hideCategorySwitch()
    fun showLoadingAction()
    fun hideLoadingAction()
}