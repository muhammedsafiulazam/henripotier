package com.muhammedsafiulazam.henripotier.cart.service

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.cart.event.CartEventType
import com.muhammedsafiulazam.henripotier.cart.model.Product
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager

class CartService : AddOn(), ICartService {
    // Products.
    private val mProducts: MutableMap<String, Product> = mutableMapOf()

    override fun addProduct(identifier: String, item: Any) {
        val product: Product

        if (mProducts.containsKey(identifier)) {
            product = mProducts.get(identifier) as Product
            product.count += 1 // Increase by one.
        } else {
            product = Product(identifier, item, 1)
            mProducts[identifier] = product
        }

        val eventManager: IEventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager
        val event = Event(CartEventType.ADD_PRODUCT, product, null)
        eventManager.send(event)
    }

    override fun removeProduct(identifier: String) {
        if (mProducts.containsKey(identifier)) {
            val product: Product = mProducts.get(identifier) as Product
            product.count -= 1
            if (product.count <= 0) {
                mProducts.remove(identifier)
            }

            val eventManager: IEventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager
            val event = Event(CartEventType.REMOVE_PRODUCT, product, null)
            eventManager.send(event)
        }
    }

    override fun getProducts() {
        val eventManager: IEventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager
        val event = Event(CartEventType.GET_PRODUCTS, mProducts.values.toList(), null)
        eventManager.send(event)
    }

    override fun countProducts() {
        val eventManager: IEventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager

        var count: Int = 0
        mProducts.values.forEach {
            count += it.count
        }

        val event = Event(CartEventType.COUNT_PRODUCTS, count, null)
        eventManager.send(event)
    }
}