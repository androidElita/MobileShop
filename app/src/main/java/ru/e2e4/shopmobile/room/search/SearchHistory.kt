package ru.e2e4.shopmobile.room.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchHistory")
open class SearchHistory(

    @ColumnInfo(name = "search_time")
    val searchTime: Long,

    @PrimaryKey
    @ColumnInfo(name = "text")
    val text: String
)