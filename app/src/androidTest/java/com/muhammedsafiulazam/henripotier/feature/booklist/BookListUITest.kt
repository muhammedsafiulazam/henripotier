package com.muhammedsafiulazam.henripotier.feature.booklist

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.core.BaseUITest
import com.muhammedsafiulazam.henripotier.core.IAfterWait
import com.muhammedsafiulazam.henripotier.core.IBeforeWait
import com.muhammedsafiulazam.henripotier.event.Event
import com.muhammedsafiulazam.henripotier.feature.booklist.event.BookListEventType
import com.muhammedsafiulazam.henripotier.network.event.book.BookEventType
import com.muhammedsafiulazam.henripotier.utils.RecyclerViewAssertion.withItemCount
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Muhammed Safiul Azam on 29/07/2019.
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class BasketUITest : BaseUITest() {

    @Rule @JvmField
    var mActivityTestRule: ActivityTestRule<BookListActivity> = ActivityTestRule(BookListActivity::class.java, true, false)

    @Before
    fun beforeTest() {
    }

    @Test
    fun checkSuccess_loadBooks() {
        wait(BookListEventType.UPDATE_DATA, object : IBeforeWait {
            override fun beforeWait() {
                val intent = Intent(getContext(), BookListActivity::class.java)
                mActivityTestRule.launchActivity(intent)

                onView(withId(R.id.booklist_pgb_loader)).check(matches(isDisplayed()))
                onView(withId(R.id.booklist_ryv_items)).check(withItemCount(0))
            }

        }, object : IAfterWait {
            override fun afterWait(events: List<Event>) {

                onView(withId(R.id.booklist_pgb_loader)).check(matches(not(isDisplayed())))
                onView(withId(R.id.booklist_ryv_items)).check(withItemCount(greaterThan(0)))
            }
        })
    }

    @After
    fun afterTest() {

    }
}