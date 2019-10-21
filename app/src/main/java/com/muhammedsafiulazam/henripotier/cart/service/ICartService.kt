package com.muhammedsafiulazam.henripotier.cart.service

import com.muhammedsafiulazam.henripotier.addon.IAddOn

interface ICartService : IAddOn {
    fun addProduct(identifier: String, item: Any)
    fun removeProduct(identifier: String)
    fun getProducts()
    fun countProducts()
}