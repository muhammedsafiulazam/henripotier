package com.muhammedsafiulazam.henripotier.network.service.contributor

import android.text.TextUtils
import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.network.event.offer.OfferEventType
import com.muhammedsafiulazam.henripotier.network.model.Error
import com.muhammedsafiulazam.henripotier.network.model.offer.Offers
import com.muhammedsafiulazam.henripotier.network.queue.IQueueManager
import com.muhammedsafiulazam.henripotier.network.server.IServerManager
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class OfferService : AddOn(), IOfferService {
    /**
     * Get offers.
     * @param isbns list of isbn
     */
    override fun getOffers(isbns: List<String>) {
        // Server manager.
        val serverManager: IServerManager? = getAddOn(AddOnType.SERVER_MANAGER) as IServerManager?

        var list: String? = ""
        isbns.forEach { it ->
            if (!TextUtils.isEmpty(list)) {
                list += ","
            }
            list += it
        }

        // Service call.
        val call: Call<Offers> = serverManager!!.getOfferServer().getOfferCall().getOffers(list)

        // Queue manager.
        val queueManager: IQueueManager? = getAddOn(AddOnType.QUEUE_MANAGER) as IQueueManager?

        // Push in queue.
        queueManager!!.execute(call as Call<Any>, callback = { response: Response<Any> ->
            var offers: Offers? = null
            var error: Error? = null

            if (response.isSuccessful()) {
                offers = (response as Response<Offers>).body()
            } else {
                error = Error(response.code(), response.errorBody()?.toString())
            }

            val event = Event(OfferEventType.GET_OFFERS, offers, error)

            // Event manager.
            val eventManager: IEventManager? = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager?
            eventManager!!.send(event)
        })
    }

}