package ru.e2e4.shopmobile.utils.resource

import android.content.res.Resources
import ru.e2e4.shopmobile.R

class AndroidResourceManager(private val resources: Resources) : ResourceManager {

    override val home: String
        get() = resources.getString(R.string.home)
}