package com.muhammedsafiulazam.henripotier.addon

import android.content.Context
import android.content.res.Resources
import com.muhammedsafiulazam.henripotier.activity.ActivityManager
import com.muhammedsafiulazam.henripotier.activity.IActivityManager
import com.muhammedsafiulazam.henripotier.cart.CartManager
import com.muhammedsafiulazam.henripotier.cart.ICartManager
import com.muhammedsafiulazam.henripotier.event.EventManager
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.network.queue.IQueueManager
import com.muhammedsafiulazam.henripotier.network.queue.QueueManager
import com.muhammedsafiulazam.henripotier.network.retrofit.IRetrofitManager
import com.muhammedsafiulazam.henripotier.network.retrofit.RetrofitManager
import com.muhammedsafiulazam.henripotier.network.server.IServerManager
import com.muhammedsafiulazam.henripotier.network.server.ServerManager
import com.muhammedsafiulazam.henripotier.network.service.IServiceManager
import com.muhammedsafiulazam.henripotier.network.service.ServiceManager

object AddOnManager : AddOn(), IAddOn {

    // Context.
    private var mContext: Context? = null

    // Activity manager.
    private val mActivityManager: IActivityManager by lazy {
        ActivityManager()
    }

    // Server manager.
    private val mServerManager: IServerManager by lazy {
        ServerManager()
    }

    // Service manager.
    private val mServiceManager: IServiceManager by lazy {
        ServiceManager()
    }

    // Event manager.
    private val mEventManager: IEventManager by lazy {
        EventManager()
    }

    // Retrofit manager.
    private val mRetrofitManger: IRetrofitManager by lazy {
        RetrofitManager()
    }

    // Queue manager.
    private val mQueueManager: IQueueManager by lazy {
        QueueManager()
    }

    // Cart manager.
    private val mCartManager: ICartManager by lazy {
        CartManager()
    }

    /**
     * Initialize with context.
     * @param context context
     */
    fun initialize(context: Context) {
        mContext = context
        onInitialize()
    }

    private fun onInitialize() {
        // Activity manager.
        addAddOn(AddOnType.ACTIVITY_MANAGER, mActivityManager)

        // Server manager.
        addAddOn(AddOnType.SERVER_MANAGER, mServerManager)

        // Service manager.
        addAddOn(AddOnType.SERVICE_MANAGER, mServiceManager)

        // Event manager.
        addAddOn(AddOnType.EVENT_MANAGER, mEventManager)

        // Retrofit manager.
        addAddOn(AddOnType.RETROFIT_MANAGER, mRetrofitManger)

        // Queue manager.
        addAddOn(AddOnType.QUEUE_MANAGER, mQueueManager)

        // Basket manager.
        addAddOn(AddOnType.CART_MANAGER, mCartManager)

        // Now assign individually.

        // Service manager.
        mServiceManager.addAddOn(AddOnType.SERVER_MANAGER, mServerManager)
        mServiceManager.addAddOn(AddOnType.EVENT_MANAGER, mEventManager)
        mServiceManager.addAddOn(AddOnType.QUEUE_MANAGER, mQueueManager)

        // Server manager.
        mServerManager.addAddOn(AddOnType.RETROFIT_MANAGER, mRetrofitManger)

        // Cart manager.
        mCartManager.addAddOn(AddOnType.EVENT_MANAGER, mEventManager)
    }

    /**
     * Get context.
     * @return context
     */
    fun getContext() : Context {
        return mContext!!
    }

    /**
     * Get resources.
     * @return resources
     */
    fun getResources() : Resources {
        return mContext!!.resources
    }
}