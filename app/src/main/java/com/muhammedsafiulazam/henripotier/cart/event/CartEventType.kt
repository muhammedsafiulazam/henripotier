package com.muhammedsafiulazam.henripotier.cart.event

object CartEventType {
    // Tag.
    const val TAG: String = "CART_EVENT_TYPE_"

    // Cart event types.
    const val ADD_PRODUCT: String = TAG + "ADD_PRODUCT"
    const val REMOVE_PRODUCT: String = TAG + "REMOVE_PRODUCT"
    const val GET_PRODUCTS: String = TAG + "GET_PRODUCTS"
    const val COUNT_PRODUCTS: String = TAG + "COUNT_PRODUCTS"
}