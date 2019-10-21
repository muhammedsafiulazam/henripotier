package com.muhammedsafiulazam.henripotier.feature.booklist

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedsafiulazam.henripotier.feature.booklist.event.BookListEventType
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.activity.BaseActivity
import com.muhammedsafiulazam.henripotier.activity.IActivityManager
import com.muhammedsafiulazam.henripotier.addon.AddOnType
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.event.IEventManager
import com.muhammedsafiulazam.henripotier.feature.basket.BasketActivity
import com.muhammedsafiulazam.henripotier.network.model.book.Book
import kotlinx.android.synthetic.main.activity_booklist.*


/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class BookListActivity : BaseActivity(), IBookListListener {
    private lateinit var eventManager: IEventManager
    private lateinit var activityManager: IActivityManager
    private val mBookList: MutableList<Book> = mutableListOf()
    private val mBookListAdapter: BookListAdapter by lazy {
        BookListAdapter(mBookList, this)
    }

    private lateinit var mMimCount: MenuItem
    private lateinit var mMimBasket: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booklist)
        setActivityModel(BookListActivityModel::class.java)

        eventManager = getAddOn(AddOnType.EVENT_MANAGER) as IEventManager
        activityManager = getAddOn(AddOnType.ACTIVITY_MANAGER) as IActivityManager

        updateMessage(null)
        updateLoader(false)

        // Initialize recycler view.
        val linearLayoutManager = LinearLayoutManager(this)
        booklist_ryv_items.setLayoutManager(linearLayoutManager)
        booklist_ryv_items.adapter = mBookListAdapter

        booklist_btn_retry.setOnClickListener(View.OnClickListener {
            onClickRetry()
        })

        receiveEvents(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_booklist, menu)
        mMimCount = menu.findItem(R.id.booklist_mim_count)
        mMimBasket = menu.findItem(R.id.booklist_mim_basket)
        mMimBasket.setOnMenuItemClickListener(object: MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {
                    onClickBasket()
                    return true
                }
            }
        )
        updateCartCount()
        return true
    }

    fun updateLoader(show: Boolean) {
        if (show) {
            booklist_pgb_loader.visibility = VISIBLE
            updateMessage(null)
        } else {
            booklist_pgb_loader.visibility = GONE
        }
    }

    fun updateMessage(message: String?) {
        if (message != null) {
            booklist_txv_message.text = message
            booklist_txv_message.visibility = VISIBLE
            booklist_btn_retry.visibility = VISIBLE
            updateLoader(false)
        } else {
            booklist_txv_message.visibility = GONE
            booklist_btn_retry.visibility = GONE
        }
    }

    fun updateView(bookList: List<Book>) {
        this.mBookList.clear()
        this.mBookList.addAll(bookList)

        this.booklist_ryv_items.post(Runnable {
            mBookListAdapter.notifyDataSetChanged()
        })
    }

    fun updateCartCount(count: Int = 0) {
        if (count <= 0) {
            mMimCount.title = ""
        } else {
            mMimCount.title = getString(R.string.booklist_label_count, count)
        }
    }

    override fun onClickAdd(book: Book) {
        requestAddBook(book)
    }

    override fun onClickBook(book: Book) {
        println("onClickBook")
    }

    private fun requestData() {
        val event = Event(BookListEventType.REQUEST_DATA, null, null)
        eventManager.send(event)
    }

    private fun requestAddBook(book: Book) {
        val event = Event(BookListEventType.REQUEST_ADD_BOOK, book, null)
        eventManager.send(event)
    }

    override fun onReceiveEvents(event: Event) {
        if (TextUtils.equals(BookListEventType.UPDATE_BUSY, event.type)) {
            updateLoader(event.data as Boolean)
        } else if (TextUtils.equals(BookListEventType.UPDATE_ERROR, event.type)) {
            updateMessage(event.data as String)
        } else if (TextUtils.equals(BookListEventType.UPDATE_DATA, event.type)) {
            val books: List<Book> = event.data as List<Book>
            updateView(books)
        } else if (TextUtils.equals(BookListEventType.UPDATE_CART_COUNT, event.type)) {
            val count: Int = event.data as Int
            updateCartCount(count)
        }
    }

    private fun onClickRetry() {
        requestData()
    }

    private fun onClickBasket() {
        activityManager.loadActivity(BasketActivity::class.java)
    }

    override fun onDestroy() {
        receiveEvents(false)
        super.onDestroy()
    }
}
