package com.muhammedsafiulazam.henripotier.feature.booklist.event

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

object BookListEventType {
    // Tag.
    const val TAG: String = "BOOKLIST_EVENT_TYPE_"

    // Book list event types.
    const val UPDATE_BUSY: String = TAG + "UPDATE_BUSY"
    const val UPDATE_ERROR: String = TAG + "UPDATE_ERROR"

    const val REQUEST_DATA: String = TAG + "REQUEST_DATA"
    const val UPDATE_DATA: String = TAG + "UPDATE_DATA"

    const val REQUEST_ADD_BOOK: String = TAG + "REQUEST_ADD_BOOK"
    const val UPDATE_CART_COUNT: String = TAG + "UPDATE_CART_COUNT"
}