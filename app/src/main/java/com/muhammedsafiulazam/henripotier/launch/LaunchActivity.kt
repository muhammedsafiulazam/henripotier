package com.muhammedsafiulazam.henripotier.launch

import android.os.Bundle
import com.muhammedsafiulazam.henripotier.feature.booklist.BookListActivity
import com.muhammedsafiulazam.henripotier.R
import com.muhammedsafiulazam.henripotier.activity.BaseActivity
import com.muhammedsafiulazam.henripotier.activity.IActivityManager
import com.muhammedsafiulazam.henripotier.addon.AddOnType

/**
 * Created by Muhammed Safiul Azam on 24/07/2019.
 */

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    override fun onStart() {
        super.onStart()

        // Entry activity.
        val activityManager: IActivityManager = getAddOn(AddOnType.ACTIVITY_MANAGER) as IActivityManager
        activityManager.loadActivity(BookListActivity::class.java)
    }
}
