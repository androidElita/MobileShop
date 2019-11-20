package ru.e2e4.shopmobile.room.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.modules.search.data.SearchType

@Entity(tableName = "searchHistory")
open class SearchHistory(

    @ColumnInfo(name = "search_time")
    val searchTime: Long,

    @PrimaryKey
    @ColumnInfo(name = "text")
    override val text: String

) : SearchItem {

    override val type
        get() = SearchType.HISTORY

    override fun areItemsTheSame(newProduct: SearchItem) = (text == newProduct.text)
}