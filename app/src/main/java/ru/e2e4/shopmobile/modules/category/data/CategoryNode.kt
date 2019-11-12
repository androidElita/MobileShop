package ru.e2e4.shopmobile.modules.category.data

import com.google.gson.annotations.SerializedName

class CategoryNode(
    @SerializedName("name") val name: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("children") val children: List<CategoryNode>,
    @SerializedName("fullName") val fullName: List<String>,
    @SerializedName("id") val id: Int?,
    @SerializedName("count") val count: Int

)