package com.muhammedsafiulazam.henripotier.network.retrofit

import com.muhammedsafiulazam.henripotier.addon.IAddOn
import retrofit2.Retrofit

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IRetrofitManager : IAddOn {
    /**
     * Get retrofit.
     * @return retrofit
     */
    fun getRetrofit() : Retrofit
}