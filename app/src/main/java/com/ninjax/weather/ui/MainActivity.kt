package com.ninjax.weather.ui

import android.os.Bundle
import com.ninjax.weather.R
import com.ninjax.weather.extension.replaceFragment
import com.ninjax.weather.ui.base.BaseActivity
import com.ninjax.weather.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    override fun createViewForActivity(savedInstanceState: Bundle?) {
        // set content view
        setContentView(R.layout.main_activity)
        // add view
        addView()
    }

    private fun addView() {
        replaceFragment(HomeFragment(), addBackStack = false)
    }
}
