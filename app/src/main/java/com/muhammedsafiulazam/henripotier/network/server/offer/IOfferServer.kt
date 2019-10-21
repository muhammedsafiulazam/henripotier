package com.muhammedsafiulazam.henripotier.network.server.offer

import com.muhammedsafiulazam.henripotier.addon.IAddOn

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IOfferServer : IAddOn {
    /**
     * Get offer call.
     * @return offer call
     */
    fun getOfferCall() : IOfferCall
}