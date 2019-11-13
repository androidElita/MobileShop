package ru.e2e4.shopmobile.modules.search.model

import io.reactivex.Single
import ru.e2e4.shopmobile.modules.search.contract.SearchModel
import ru.e2e4.shopmobile.room.search.SearchHistory
import ru.e2e4.shopmobile.room.search.SearchHistoryDao
import javax.inject.Inject

class SearchModelImpl @Inject constructor(
    private val searchHistoryTable: SearchHistoryDao
) : SearchModel {

    override fun getSearchHistory(): Single<List<SearchHistory>> {
        return searchHistoryTable.getSearchHistory()
    }

    override fun addSearchHistory(text: String) {
        searchHistoryTable.insert(SearchHistory(text))
    }

    override fun cleanHistory() {
        searchHistoryTable.deleteAll()
    }
}