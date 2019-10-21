package com.muhammedsafiulazam.henripotier.network.service.book

import com.muhammedsafiulazam.henripotier.addon.IAddOn

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IBookService : IAddOn {
    /**
     * Get bookList.
     */
    fun getBooks()
}