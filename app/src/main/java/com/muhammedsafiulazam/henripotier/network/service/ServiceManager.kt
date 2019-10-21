package com.muhammedsafiulazam.henripotier.network.service

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.network.service.contributor.OfferService
import com.muhammedsafiulazam.henripotier.network.service.contributor.IOfferService
import com.muhammedsafiulazam.henripotier.network.service.book.IBookService
import com.muhammedsafiulazam.henripotier.network.service.book.BookService

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class ServiceManager : AddOn(), IServiceManager {

    private val mBookService: IBookService by lazy {
        val bookService = BookService()
        bookService.addAddOns(getAddOns())
        bookService
    }
    private val mOfferService: IOfferService by lazy {
        val offerService = OfferService()
        offerService.addAddOns(getAddOns())
        offerService
    }

    /**
     * Get book service.
     * @return book service
     */
    override fun getBookService(): IBookService {
        return mBookService
    }

    /**
     * Get offer service.
     * @return offer service
     */
    override fun getOfferService(): IOfferService {
        return mOfferService
    }

    /**
     * Clear addons.
     */
    override fun clearAddOns() {
        mBookService.clearAddOns()
        mOfferService.clearAddOns()
        super.clearAddOns()
    }
}