package com.muhammedsafiulazam.henripotier.network.service

import com.muhammedsafiulazam.henripotier.addon.IAddOn
import com.muhammedsafiulazam.henripotier.network.service.contributor.IOfferService
import com.muhammedsafiulazam.henripotier.network.service.book.IBookService

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IServiceManager : IAddOn {
    /**
     * Get book service.
     * @return book service
     */
    fun getBookService() : IBookService

    /**
     * Get offer service.
     * @return offer service
     */
    fun getOfferService() : IOfferService
}