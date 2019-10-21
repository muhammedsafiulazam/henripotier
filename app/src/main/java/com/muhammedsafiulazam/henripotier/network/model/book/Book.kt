package com.muhammedsafiulazam.henripotier.network.model.book

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

@Parcelize
data class Book (
    @field:Json(name = "isbn") val isbn: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "price") val price: Float?,
    @field:Json(name = "cover") val cover: String?,
    @field:Json(name = "synopsis") val synopsis: List<String>?
) : Parcelable