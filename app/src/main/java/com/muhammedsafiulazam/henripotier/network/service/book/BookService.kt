package com.muhammedsafiulazam.henripotier.network.service.book

import com.muhammedsafiulazam.henripotier.addon.AddOn
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.network.event.book.BookEventType
import com.muhammedsafiulazam.henripotier.network.model.Error
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import com.muhammedsafiulazam.henripotier.network.queue.IQueueManager
import com.muhammedsafiulazam.henripotier.network.server.IServerManager
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookService : AddOn(), IBookService {
    /**
     * Get bookList.
     */
    override fun getBooks() {
        // Server manager.
        val serverManager: IServerManager? = getAddOn(AddOnType.SERVER_MANAGER) as IServerManager?

        // Service call.
        val call: Call<List<Book>> = serverManager!!.getBookServer().getBookCall().getBooks()

        // Queue manager.
        val queueManager: IQueueManager? = getAddOn(AddOnType.QUEUE_MANAGER) as IQueueManager?

        // Push in queue.
        queueManager!!.execute(call as Call<Any>, callback = { response: Response<Any> ->
            var books: List<Book>? = null
            var error: Error? = null

            if (response.isSuccessful()) {
                books = (response as Response<List<Book>>).body()
            } else {
                error = Error(response.code(), response.errorBody()?.toString())
            }

            val event = Event(BookEventType.GET_BOOKS, books, error)

            // Event manager.
            val eventManager: IEventManager? = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager?
            eventManager!!.send(event)
        })
    }
}