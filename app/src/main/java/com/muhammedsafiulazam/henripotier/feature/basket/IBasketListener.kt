package com.muhammedsafiulazam.henripotier.feature.basket

import com.muhammedsafiulazam.henripotier.cart.model.Product

interface IBasketListener {
    fun onClickRemove(product: Product)
    fun onClickProduct(product: Product)
}