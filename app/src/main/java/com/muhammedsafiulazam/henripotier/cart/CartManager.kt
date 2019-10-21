package com.muhammedsafiulazam.henripotier.cart

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.cart.service.CartService
import com.muhammedsafiulazam.henripotier.cart.service.ICartService

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class CartManager : AddOn(), ICartManager {

    private val mBasket: ICartService by lazy {
        val cartService = CartService()
        cartService.addAddOns(getAddOns())
        cartService
    }

    /**
     * Get cart service.
     * @return cart service
     */
    override fun getCartService(): ICartService {
        return mBasket
    }

    /**
     * Clear addons.
     */
    override fun clearAddOns() {
        mBasket.clearAddOns()
        super.clearAddOns()
    }
}