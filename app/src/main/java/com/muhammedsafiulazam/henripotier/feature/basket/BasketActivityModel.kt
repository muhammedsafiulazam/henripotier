package com.muhammedsafiulazam.henripotier.feature.basket

import android.text.TextUtils
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.activity.BaseActivityModel
import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnManager
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.cart.ICartManager
import com.muhammedsafiulazam.henripotier.cart.event.CartEventType
import com.muhammedsafiulazam.henripotier.cart.model.Product
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.feature.basket.event.BasketEventType
import com.muhammedsafiulazam.henripotier.feature.basket.model.Basket
import com.muhammedsafiulazam.henripotier.network.event.offer.OfferEventType
import com.muhammedsafiulazam.henripotier.network.model.offer.Offer
import com.muhammedsafiulazam.henripotier.network.model.offer.Offers
import com.muhammedsafiulazam.henripotier.network.service.IServiceManager

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BasketActivityModel : BaseActivityModel() {
    private lateinit var eventManager: IEventManager
    private lateinit var serviceManager: IServiceManager
    private lateinit var cartManager: ICartManager
    private var mProducts: MutableList<Product> = mutableListOf()

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

        // Load products.
        cartManager.getCartService().getProducts()
    }

    private fun updateBusy(busy: Boolean) {
        val event = Event(BasketEventType.UPDATE_BUSY, busy, null)
        eventManager.send(event)
    }

    private fun updateError(error: String?) {
        val event = Event(BasketEventType.UPDATE_ERROR, error, null)
        eventManager.send(event)
    }

    private fun updateData(response: Any?) {
        val event = Event(BasketEventType.UPDATE_DATA, response, null)
        eventManager.send(event)
    }

    fun requestRemoveBook(product: Product) {
        // Remove book.
        cartManager.getCartService().removeProduct(product.identifier)
    }

    fun requestOffer() {
        // Show loader.
        updateBusy(true)

        val isbns: MutableList<String> = mutableListOf()
        mProducts.forEach {
            isbns.add(it.identifier)
        }

        serviceManager.getOfferService().getOffers(isbns)
    }

    override fun onReceiveEvents(event: Event) {
        if (TextUtils.equals(BasketEventType.REQUEST_DATA, event.type)
            || TextUtils.equals(CartEventType.ADD_PRODUCT, event.type)
            || TextUtils.equals(CartEventType.REMOVE_PRODUCT, event.type)) {
            requestData()
        }
        else if (TextUtils.equals(BasketEventType.REQUEST_REMOVE_BOOK, event.type)) {
            requestRemoveBook(event.data as Product)
        }
        else if (TextUtils.equals(CartEventType.GET_PRODUCTS, event.type)) {
            mProducts.clear()
            if (event.error != null) {
                updateBusy(false)
                updateData(null)
                updateError(getActivity()?.getString(R.string.basket_error_load))
            } else {
                mProducts.addAll(event.data as List<Product>)
                requestOffer()
            }
        } else if (TextUtils.equals(OfferEventType.GET_OFFERS, event.type)) {
            updateBusy(false)
            if (event.error != null) {
                mProducts.clear()
                updateData(null)
                updateError(getActivity()?.getString(R.string.basket_error_load))
            } else {
                val basket: Basket = Basket(mProducts, event.data as? Offers)
                updateData(basket)
            }
        }
    }

    override fun onDestroyActivity() {
        receiveEvents(false)
        super.onDestroyActivity()
    }
}