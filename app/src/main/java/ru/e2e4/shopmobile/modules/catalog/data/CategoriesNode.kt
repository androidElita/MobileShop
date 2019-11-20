package ru.e2e4.shopmobile.modules.catalog.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoriesNode(
    @SerializedName("name") val name: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("children") val children: List<CategoriesNode>,
    @SerializedName("fullName") val fullName: List<String>,
    @SerializedName("id") val id: Int?,
    @SerializedName("count") val count: Int
) : Parcelable