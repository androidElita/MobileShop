package ru.e2e4.shopmobile.room.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single

@Dao
interface SearchHistoryDao {

    @Insert
    fun insert(history: SearchHistory)

    @Update
    fun update(history: SearchHistory)

    @Query("""SELECT * FROM (
             SELECT * FROM searchHistory ORDER BY search_time DESC LIMIT $COUNT_HISTORY)
             ORDER BY search_time DESC""")
    fun getSearchHistory(): Single<List<SearchHistory>>

    @Query("DELETE FROM searchHistory")
    fun deleteAll()

    companion object{
        const val COUNT_HISTORY = 5
    }
}