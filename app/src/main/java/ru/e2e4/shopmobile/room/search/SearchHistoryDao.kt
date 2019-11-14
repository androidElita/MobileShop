package ru.e2e4.shopmobile.room.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface SearchHistoryDao {

    @Insert
    fun insert(history: SearchHistory)

    @Query("SELECT * FROM searchHistory")
    fun getSearchHistory(): Single<List<SearchHistory>>

    @Query("DELETE FROM searchHistory")
    fun deleteAll()
}