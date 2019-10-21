package com.muhammedsafiulazam.henripotier.feature.basket.event

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

object BasketEventType {
    // Tag.
    const val TAG: String = "BASKET_EVENT_TYPE_"

    // Basket event types.
    const val UPDATE_BUSY: String = TAG + "UPDATE_BUSY"
    const val UPDATE_ERROR: String = TAG + "UPDATE_ERROR"

    const val REQUEST_DATA: String = TAG + "REQUEST_DATA"
    const val UPDATE_DATA: String = TAG + "UPDATE_DATA"

    const val REQUEST_REMOVE_BOOK: String = TAG + "REQUEST_REMOVE_BOOK"
}