package com.muhammedsafiulazam.henripotier.feature.booklist

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookListViewHolder(view: View, bookListListener: IBookListListener) : RecyclerView.ViewHolder(view){
    private var mView: View
    private var mTxvTitle: AppCompatTextView
    private var mTxvSynopsis: AppCompatTextView
    private var mTxvPrice: AppCompatTextView
    private var mImvCover: AppCompatImageView
    private var mImvAdd: AppCompatImageView
    private var mPgbCover: ProgressBar
    private var mBook: Book? = null

    init {
        mView = view
        mTxvTitle = view.findViewById(R.id.book_txv_title)
        mTxvSynopsis = view.findViewById(R.id.book_txv_synopsis)
        mTxvPrice = view.findViewById(R.id.book_txv_price)
        mImvCover = view.findViewById(R.id.book_imv_cover)
        mImvAdd = view.findViewById(R.id.book_imv_add)
        mPgbCover = view.findViewById(R.id.book_pgb_cover)
        mPgbCover.visibility = View.GONE

        mImvAdd.setOnClickListener {
            bookListListener.onClickAdd(mBook!!)
        }

        view.setOnClickListener {
            bookListListener.onClickBook(mBook!!)
        }
    }

    fun bind(book: Book) {
        mBook = book

        mTxvTitle.text = book.title
        mTxvSynopsis.text = book.synopsis!!.first()
        mTxvPrice.text = mView.context.getString(R.string.booklist_label_price, book.price)

        mPgbCover.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            Picasso.get().load(book.cover).into(mImvCover, object: Callback {
                override fun onSuccess() {
                    mPgbCover.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                    mPgbCover.visibility = View.GONE
                }
            })
        }
    }
}