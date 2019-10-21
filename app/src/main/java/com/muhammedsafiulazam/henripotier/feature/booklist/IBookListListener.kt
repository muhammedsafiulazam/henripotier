package com.muhammedsafiulazam.henripotier.feature.booklist

import com.muhammedsafiulazam.henripotier.network.model.book.Book

interface IBookListListener {
    fun onClickAdd(book: Book)
    fun onClickBook(book: Book)
}