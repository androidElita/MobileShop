package ru.e2e4.shopmobile.modules.search.data

class SearchResult(override val text: String) : SearchItem {

    override val type
        get() = SearchType.SEARCH_RESULT

    override fun areItemsTheSame(newProduct: SearchItem): Boolean {
        return text == newProduct.text
    }
}