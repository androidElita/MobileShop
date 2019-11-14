package ru.e2e4.shopmobile.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.e2e4.shopmobile.room.search.SearchHistory
import ru.e2e4.shopmobile.room.search.SearchHistoryDao

@Database(entities = [SearchHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSearchHistoryDao(): SearchHistoryDao
}