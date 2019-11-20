package ru.e2e4.shopmobile.modules.catalog.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRestService {

    @GET(PATH + "list")
    fun getCategoryTree(
        @Query("zoneId") zoneId: Int
    ): Single<CategoriesNode>

    companion object {
        const val PATH = "category/"
    }
}