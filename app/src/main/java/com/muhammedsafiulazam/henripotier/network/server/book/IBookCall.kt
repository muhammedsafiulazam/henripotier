package com.muhammedsafiulazam.henripotier.network.server.book

import com.muhammedsafiulazam.henripotier.network.model.book.Book
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface IBookCall {
    /**
     * Get bookList.
     * @return list of book
     */
    @GET("books")
    fun getBooks() : Call<List<Book>>
}