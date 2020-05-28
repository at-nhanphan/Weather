package com.ninjax.weather.ui.base

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.ninjax.weather.R
import com.ninjax.weather.extension.getNavigationBarHeight

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // full screen
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        // set Content View
        createViewForActivity(savedInstanceState)
        // check navigation bar Back/Home/Multiple task
        checkNavigationBar()
    }

    open fun createViewForActivity(savedInstanceState: Bundle?) {
        getLayoutResource()?.run { setContentView(this) }
    }

    @LayoutRes
    open fun getLayoutResource(): Int? = null

    private fun hasNavigationBar(): Boolean {
        val d = windowManager.defaultDisplay

        val realDisplayMetrics = DisplayMetrics()
        d.getRealMetrics(realDisplayMetrics)

        val realHeight = realDisplayMetrics.heightPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels

        return realHeight - displayHeight > 0
    }

    private fun checkNavigationBar() {
        if (hasNavigationBar()) {
            //padding bottom if has navigation bar
            val parent = findViewById<View>(R.id.masterPage)
            parent?.let {
                it.setPadding(
                    it.paddingStart,
                    it.paddingTop,
                    it.paddingEnd,
                    getNavigationBarHeight()
                )
            }
        }
    }
}
