package com.muhammedsafiulazam.henripotier.network.model.offer

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

@Parcelize
data class Offer (
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "value") val value: Float?,
    @field:Json(name = "sliceValue") val sliceValue: Float?
) : Parcelable {

    companion object {
        const val TYPE_MINUS = "minus"
        const val TYPE_PERCENTAGE = "percentage"
        const val TYPE_SLICE = "slice"
    }
}