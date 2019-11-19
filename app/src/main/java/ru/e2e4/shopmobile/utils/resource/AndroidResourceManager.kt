package ru.e2e4.shopmobile.utils.resource

import android.content.res.Resources
import ru.e2e4.shopmobile.R

class AndroidResourceManager(private val resources: Resources) : ResourceManager {

    override val found: List<String>
        get() = getListByResource(R.array.find)

    override val goods: List<String>
        get() = getListByResource(R.array.goods)

    override val searchHistoryEmpty: String
        get() = resources.getString(R.string.searchHistoryEmpty)

    override val searchEmpty: String
        get() = resources.getString(R.string.searchEmpty)

    private fun getListByResource(id: Int): List<String> {
        return resources.getTextArray(id)
            .toList()
            .map { it.toString() }
    }
}