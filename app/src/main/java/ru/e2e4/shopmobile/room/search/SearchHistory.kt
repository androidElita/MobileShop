package ru.e2e4.shopmobile.room.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.e2e4.shopmobile.utils.recycler.DiffUtilItem

@Entity(tableName = "searchHistory")
open class SearchHistory(

    @ColumnInfo(name = "search_time")
    val searchTime: Long,

    @PrimaryKey
    @ColumnInfo(name = "text")
    val text: String

) : DiffUtilItem<SearchHistory> {

    override fun areItemsTheSame(newProduct: SearchHistory): Boolean {
        return text == newProduct.text
    }

    override fun areContentsTheSame(newProduct: SearchHistory): Boolean {
        return searchTime == newProduct.searchTime
    }
}