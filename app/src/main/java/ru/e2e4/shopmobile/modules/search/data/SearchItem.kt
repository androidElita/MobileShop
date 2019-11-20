package ru.e2e4.shopmobile.modules.search.data

import ru.e2e4.shopmobile.utils.recycler.DiffUtilItem

interface SearchItem : DiffUtilItem<SearchItem> {
    val text: String
    val type: SearchType
}