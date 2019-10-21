package com.muhammedsafiulazam.henripotier.network.server

import com.muhammedsafiulazam.henripotier.addon.IAddOn
import com.muhammedsafiulazam.henripotier.network.server.offer.IOfferServer
import com.muhammedsafiulazam.henripotier.network.server.book.IBookServer

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IServerManager : IAddOn {
    /**
     * Get book server.
     * @return book server
     */
    fun getBookServer() : IBookServer

    /**
     * Get offer server.
     * @return offer server
     */
    fun getOfferServer() : IOfferServer
}