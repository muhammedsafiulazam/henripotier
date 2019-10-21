package com.muhammedsafiulazam.henripotier.network.server.book

import com.muhammedsafiulazam.henripotier.addon.IAddOn

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IBookServer : IAddOn {
    /**
     * Get book call.
     * @return book call
     */
    fun getBookCall() : IBookCall
}