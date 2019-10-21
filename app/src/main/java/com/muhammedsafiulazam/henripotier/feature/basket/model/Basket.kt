package com.muhammedsafiulazam.henripotier.feature.basket.model

import com.muhammedsafiulazam.henripotier.cart.model.Product
import com.muhammedsafiulazam.henripotier.network.model.offer.Offers

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

data class Basket constructor(
    var products: List<Product>? = null,
    var offers: Offers? = null
)