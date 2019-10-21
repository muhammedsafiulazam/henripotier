package com.muhammedsafiulazam.henripotier.network.server.book

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.network.retrofit.IRetrofitManager

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookServer : AddOn(), IBookServer {
    // Book calls.
    private val mBookCall : IBookCall by lazy {
        val retrofitManager: IRetrofitManager? = getAddOn(AddOnType.RETROFIT_MANAGER) as IRetrofitManager?
        retrofitManager!!.getRetrofit().create(IBookCall::class.java)
    }

    /**
     * Get repository calls.
     * @return repository calls
     */
    override fun getBookCall() : IBookCall {
        return mBookCall
    }
}