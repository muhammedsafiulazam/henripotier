package com.muhammedsafiulazam.henripotier.feature.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.cart.model.Product

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BasketAdapter(val productList: MutableList<Product>, val basketListener: IBasketListener) : RecyclerView.Adapter<BasketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false),
            basketListener
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product: Product = productList.get(position);
        holder.bind(product)
    }
}