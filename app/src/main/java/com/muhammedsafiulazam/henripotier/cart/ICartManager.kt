package com.muhammedsafiulazam.henripotier.cart

import com.muhammedsafiulazam.henripotier.addon.IAddOn
import com.muhammedsafiulazam.henripotier.cart.service.ICartService

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

interface ICartManager : IAddOn {
    /**
     * Get cart service.
     * @return cart service
     */
    fun getCartService() : ICartService
}