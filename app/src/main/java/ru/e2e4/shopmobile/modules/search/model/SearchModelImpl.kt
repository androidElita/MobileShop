package ru.e2e4.shopmobile.modules.search.model

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.Single
import ru.e2e4.shopmobile.modules.search.contract.SearchModel
import ru.e2e4.shopmobile.room.search.SearchHistory
import ru.e2e4.shopmobile.room.search.SearchHistoryDao
import java.util.*
import javax.inject.Inject

class SearchModelImpl @Inject constructor(
    private val searchHistoryTable: SearchHistoryDao
) : SearchModel {

    override fun getSearchHistory(): Single<List<SearchHistory>> {
        return searchHistoryTable.getSearchHistory()
    }

    override fun addSearchHistory(text: String) {
        if (text.isEmpty()) return
        val searchHistory = SearchHistory(Date().time, text)
        try {
            searchHistoryTable.insert(searchHistory)
        } catch (exp: SQLiteConstraintException) {
            searchHistoryTable.update(searchHistory)
        }
    }

    override fun cleanHistory() {
        searchHistoryTable.deleteAll()
    }
}