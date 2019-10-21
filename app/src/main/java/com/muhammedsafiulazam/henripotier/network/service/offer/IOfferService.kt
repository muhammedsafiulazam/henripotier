package com.muhammedsafiulazam.henripotier.network.service.contributor

import com.muhammedsafiulazam.henripotier.addon.IAddOn

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IOfferService : IAddOn {
    /**
     * Get offers.
     * @param isbns list of isbn
     */
    fun getOffers(isbns: List<String>)
}