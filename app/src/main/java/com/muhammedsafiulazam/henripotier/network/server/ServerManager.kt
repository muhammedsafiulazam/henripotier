package com.muhammedsafiulazam.henripotier.network.server

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.network.server.offer.OfferServer
import com.muhammedsafiulazam.henripotier.network.server.offer.IOfferServer
import com.muhammedsafiulazam.henripotier.network.server.book.IBookServer
import com.muhammedsafiulazam.henripotier.network.server.book.BookServer

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class ServerManager : AddOn(), IServerManager {

    private val mRepositoryServer: IBookServer by lazy {
        val repositoryServer = BookServer()
        repositoryServer.addAddOns(getAddOns())
        repositoryServer
    }

    private val mContributorServer: IOfferServer by lazy {
        val contributorServer = OfferServer()
        contributorServer.addAddOns(getAddOns())
        contributorServer
    }

    /**
     * Get repository server.
     * @return repository server
     */
    override fun getBookServer(): IBookServer {
        return mRepositoryServer
    }

    /**
     * Get contributor server.
     * @return contributor server
     */
    override fun getOfferServer(): IOfferServer {
        return mContributorServer
    }

    /**
     * Clear addons.
     */
    override fun clearAddOns() {
        mRepositoryServer.clearAddOns()
        mContributorServer.clearAddOns()
        super.clearAddOns()
    }
}