package com.muhammedsafiulazam.henripotier.network.server.offer

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.network.retrofit.IRetrofitManager

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class OfferServer : AddOn(), IOfferServer {
    // Offer call.
    private val mOfferCall : IOfferCall by lazy {
        val retrofitManager: IRetrofitManager? = getAddOn(AddOnType.RETROFIT_MANAGER) as IRetrofitManager?
        retrofitManager!!.getRetrofit().create(IOfferCall::class.java)
    }

    /**
     * Get offer calls.
     * @return offer calls
     */
    override fun getOfferCall() : IOfferCall {
        return mOfferCall
    }
}