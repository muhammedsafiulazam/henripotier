package com.muhammedsafiulazam.henripotier.feature.booklist

import android.text.TextUtils
import com.muhammedsafiulazam.henripotier.feature.booklist.event.BookListEventType
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.activity.BaseActivityModel
import com.muhammedsafiulazam.henripotier.addon.AddOnManager
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.cart.ICartManager
import com.muhammedsafiulazam.henripotier.cart.event.CartEventType
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.network.event.book.BookEventType
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import com.muhammedsafiulazam.henripotier.network.service.IServiceManager

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookListActivityModel : BaseActivityModel() {
    private lateinit var eventManager: IEventManager
    private lateinit var serviceManager: IServiceManager
    private lateinit var cartManager: ICartManager

    override fun onCreateActivity() {
        super.onCreateActivity()

        eventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager
        serviceManager = getAddOn(AddOnType.SERVICE_MANAGER) as IServiceManager
        cartManager = AddOnManager.getAddOn(AddOnType.CART_MANAGER) as ICartManager

        receiveEvents(true)
        requestData()
    }

    fun requestData() {
        // Show loader.
        updateBusy(true)

        // Load books.
        serviceManager.getBookService().getBooks()
    }

    private fun updateBusy(busy: Boolean) {
        val event = Event(BookListEventType.UPDATE_BUSY, busy, null)
        eventManager.send(event)
    }

    private fun updateError(error: String?) {
        val event = Event(BookListEventType.UPDATE_ERROR, error, null)
        eventManager.send(event)
    }

    private fun updateData(response: Any?) {
        val event = Event(BookListEventType.UPDATE_DATA, response, null)
        eventManager.send(event)
    }

    fun requestAddBook(book: Book) {
        cartManager.getCartService().addProduct(book.isbn!!, book)
    }

    fun requestCartCount() {
        cartManager.getCartService().countProducts()
    }

    fun updateCartCount(count: Int) {
        val event = Event(BookListEventType.UPDATE_CART_COUNT, count, null)
        eventManager.send(event)
    }

    override fun onReceiveEvents(event: Event) {
        if (TextUtils.equals(BookListEventType.REQUEST_DATA, event.type)) {
            requestData()
        } else if (TextUtils.equals(BookListEventType.REQUEST_ADD_BOOK, event.type)) {
            requestAddBook(event.data as Book)
        } else if (TextUtils.equals(BookEventType.GET_BOOKS, event.type)) {

            // Hide loader.
            updateBusy(false)

            if (event.error != null) {
                // Show message.
                updateError(getActivity()?.getString(R.string.booklist_error_load))
            } else {
                // Send reponse.
                updateData(event.data)
            }

            requestCartCount()

        } else if (TextUtils.equals(CartEventType.ADD_PRODUCT, event.type)
            || TextUtils.equals(CartEventType.REMOVE_PRODUCT, event.type)) {
            requestCartCount()
        } else if (TextUtils.equals(CartEventType.COUNT_PRODUCTS, event.type)) {
            updateCartCount(event.data as Int)
        }
    }

    override fun onDestroyActivity() {
        receiveEvents(false)
        super.onDestroyActivity()
    }
}