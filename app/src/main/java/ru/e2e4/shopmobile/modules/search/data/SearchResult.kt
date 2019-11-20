package ru.e2e4.shopmobile.modules.search.data

import ru.e2e4.shopmobile.R

class SearchResult(override val text: String) : SearchItem {

    override val icon: Int
        get() = R.drawable.ic_search

    override fun areItemsTheSame(newProduct: SearchItem): Boolean {
        return text == newProduct.text
    }
}