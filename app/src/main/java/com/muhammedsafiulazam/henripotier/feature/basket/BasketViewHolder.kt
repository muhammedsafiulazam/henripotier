package com.muhammedsafiulazam.henripotier.feature.basket

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.cart.model.Product
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BasketViewHolder(view: View, basketListener: IBasketListener) : RecyclerView.ViewHolder(view){
    private var mView: View
    private var mTxvTitle: AppCompatTextView
    private var mTxvPrice: AppCompatTextView
    private var mTxvCount: AppCompatTextView
    private var mImvCover: AppCompatImageView
    private var mImvRemove: AppCompatImageView
    private var mPgbCover: ProgressBar
    private var mProduct: Product? = null

    init {
        mView = view
        mTxvTitle = view.findViewById(R.id.product_txv_title)
        mTxvPrice = view.findViewById(R.id.product_txv_price)
        mTxvCount = view.findViewById(R.id.product_txv_count)
        mImvCover = view.findViewById(R.id.product_imv_cover)
        mImvRemove = view.findViewById(R.id.product_imv_remove)
        mPgbCover = view.findViewById(R.id.product_pgb_cover)
        mPgbCover.visibility = View.GONE

        mImvRemove.setOnClickListener {
            basketListener.onClickRemove(mProduct!!)
        }

        view.setOnClickListener {
            basketListener.onClickProduct(mProduct!!)
        }
    }

    fun bind(product: Product) {
        mProduct = product

        val book: Book = mProduct!!.data as Book
        val count: Int = mProduct!!.count as Int

        mTxvTitle.text = book.title
        mTxvPrice.text = mView.context.getString(R.string.basket_product_label_price, book.price)
        mTxvCount.text = mView.context.getString(R.string.basket_product_label_count, count)

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