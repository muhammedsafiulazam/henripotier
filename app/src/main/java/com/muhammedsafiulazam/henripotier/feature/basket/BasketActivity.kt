package com.muhammedsafiulazam.henripotier.feature.basket

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.activity.BaseActivity
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.cart.model.Product
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.feature.basket.event.BasketEventType
import com.muhammedsafiulazam.henripotier.feature.basket.model.Basket
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import com.muhammedsafiulazam.henripotier.network.model.offer.Offer
import com.muhammedsafiulazam.henripotier.network.model.offer.Offers
import kotlinx.android.synthetic.main.activity_basket.*


/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BasketActivity : BaseActivity(), IBasketListener {
    private lateinit var eventManager: IEventManager
    private var mBasket: Basket? = null
    private var mOffers: Offers? = null
    private val mProducts: MutableList<Product> = mutableListOf()
    private val mBasketAdapter: BasketAdapter by lazy {
        BasketAdapter(mProducts, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_basket)
        setActivityModel(BasketActivityModel::class.java)

        eventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager

        updateMessage(null)
        updateLoader(false)

        // Initialize recycler view.
        val linearLayoutManager = LinearLayoutManager(this)
        basket_ryv_items.setLayoutManager(linearLayoutManager)
        basket_ryv_items.adapter = mBasketAdapter

        basket_btn_retry.setOnClickListener(View.OnClickListener {
            onClickRetry()
        })

        receiveEvents(true)
    }

    fun updateLoader(show: Boolean) {
        if (show) {
            basket_pgb_loader.visibility = VISIBLE
            updateMessage(null)
            updateBuy(false)
        } else {
            basket_pgb_loader.visibility = GONE
        }
    }

    fun updateMessage(message: String?) {
        if (message != null) {
            basket_txv_message.text = message
            basket_txv_message.visibility = VISIBLE
            basket_btn_retry.visibility = VISIBLE
            updateLoader(false)
            updateBuy(false)
        } else {
            basket_txv_message.visibility = GONE
            basket_btn_retry.visibility = GONE
        }
    }

    fun updateView(basket: Basket?) {
        mBasket = basket
        mProducts.clear()
        if (mBasket?.products != null) {
            mProducts.addAll(mBasket?.products!!)
        }
        mOffers = mBasket?.offers

        basket_ryv_items.post(Runnable {
            mBasketAdapter.notifyDataSetChanged()
        })

        val price: Float = calculatePrice(mProducts)
        var reduction: Float = 0f

        if (mOffers == null || mOffers?.offers.isNullOrEmpty()) {
            basket_txv_offer_value.text = getString(R.string.basket_label_empty)
        } else {

            val offer: Offer = mOffers!!.offers!!.first()

            if (TextUtils.equals(Offer.TYPE_MINUS, offer.type)) {
                reduction = offer.value!!
            } else if (TextUtils.equals(Offer.TYPE_PERCENTAGE, offer.type)) {
                reduction = price * (offer.value!! / 100.0f)
            } else if (TextUtils.equals(Offer.TYPE_SLICE, offer.type)) {
                reduction = (price % offer.sliceValue!!) * offer.value!!
            }
        }

        val pay: Float = price - reduction

        if (price <= 0) {
            basket_txv_price_value.text = getString(R.string.basket_label_empty)
        } else {
            basket_txv_price_value.text = getString(R.string.basket_label_currency, price)
        }

        if (reduction <= 0) {
            basket_txv_offer_value.text = getString(R.string.basket_label_empty)
        } else {
            basket_txv_offer_value.text = getString(R.string.basket_label_currency, reduction)
        }

        if (pay <= 0) {
            basket_txv_pay_value.text = getString(R.string.basket_label_empty)
        } else {
            basket_txv_pay_value.text = getString(R.string.basket_label_currency, pay)
        }

        updateBuy(true)
    }

    private fun updateBuy(buy: Boolean) {
        basket_btn_buy.isEnabled = buy
    }

    private fun calculatePrice(products: List<Product>) : Float {
        var price = 0f
        products.forEach {
            val book: Book = it.data as Book
            price += book.price?.times(it.count)!!
        }
        return price
    }

    override fun onClickRemove(product: Product) {
        requestRemoveBook(product)
    }

    override fun onClickProduct(product: Product) {
        println("onClickProduct")
    }

    private fun requestData() {
        val event = Event(BasketEventType.REQUEST_DATA, null, null)
        eventManager.send(event)
    }

    private fun requestRemoveBook(product: Product) {
        val event = Event(BasketEventType.REQUEST_REMOVE_BOOK, product, null)
        eventManager.send(event)
    }

    override fun onReceiveEvents(event: Event) {
        if (TextUtils.equals(BasketEventType.UPDATE_BUSY, event.type)) {
            updateLoader(event.data as Boolean)
        } else if (TextUtils.equals(BasketEventType.UPDATE_ERROR, event.type)) {
            updateMessage(event.data as String)
        } else if (TextUtils.equals(BasketEventType.UPDATE_DATA, event.type)) {
            updateView(event.data as? Basket)
        }
    }

    private fun onClickRetry() {
        requestData()
    }

    override fun onDestroy() {
        receiveEvents(false)
        super.onDestroy()
    }
}
