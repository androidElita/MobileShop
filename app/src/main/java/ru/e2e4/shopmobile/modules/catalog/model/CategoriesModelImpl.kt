package ru.e2e4.shopmobile.modules.catalog.model

import android.content.SharedPreferences
import io.reactivex.Single
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryModel
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode
import ru.e2e4.shopmobile.modules.catalog.data.CategoryRestService
import javax.inject.Inject

class CategoriesModelImpl
@Inject constructor(
    private val service: CategoryRestService,
    private val sharedPref: SharedPreferences
) : CategoryModel {

    override fun loadCategoriesTree(): Single<CategoriesNode> {
        //TODO KEY_ZONE_ID, KEY_ZONE_ID_DEFAULT
        return service.getCategoryTree(sharedPref.getInt("KEY_ZONE_ID", 1))
    }
}