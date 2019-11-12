package ru.e2e4.shopmobile.modules.category.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRestService {

    @GET(PATH + "list")
    fun getCategoryTree(
        @Query("zoneId") zoneId: Int
    ): Single<CategoryNode>

    companion object {
        const val PATH = "category/"
    }
}