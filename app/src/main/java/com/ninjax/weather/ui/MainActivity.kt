package com.ninjax.weather.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ninjax.weather.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
    }
}
