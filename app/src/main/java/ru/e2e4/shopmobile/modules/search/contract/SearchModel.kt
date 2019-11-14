package ru.e2e4.shopmobile.modules.search.contract

import io.reactivex.Single
import ru.e2e4.shopmobile.room.search.SearchHistory

interface SearchModel {

    fun getSearchHistory(): Single<List<SearchHistory>>
    fun addSearchHistory(text: String)
    fun cleanHistory()
}