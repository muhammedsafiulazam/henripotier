package com.muhammedsafiulazam.henripotier.network.server.offer

import com.muhammedsafiulazam.henripotier.network.model.offer.Offers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IOfferCall {
    /**
     * Get offers.
     * @param isbns list of isbn
     * @return list of offer
     */
    @GET("books/{isbns}/commercialOffers")
    fun getOffers(@Path("isbns", encoded = true) isbns: String?) : Call<Offers>
}