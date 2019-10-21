package com.muhammedsafiulazam.henripotier.feature.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.network.model.book.Book

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookListAdapter(val bookList: MutableList<Book>, val bookListListener: IBookListListener) : RecyclerView.Adapter<BookListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_book_item, parent, false),
            bookListListener
        )
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val book: Book = bookList.get(position);
        holder.bind(book)
    }
}