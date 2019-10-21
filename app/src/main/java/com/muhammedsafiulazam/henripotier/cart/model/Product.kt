package com.muhammedsafiulazam.henripotier.cart.model

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

data class Product constructor(
    val identifier: String,
    var data: Any? = null,
    var count: Int = 0
)