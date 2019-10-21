package com.muhammedsafiulazam.henripotier.network.model.offer

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

@Parcelize
data class Offers (
    @field:Json(name = "offers") val offers: List<Offer>?
) : Parcelable