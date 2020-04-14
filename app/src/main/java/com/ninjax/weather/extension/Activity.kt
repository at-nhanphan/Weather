package com.ninjax.weather.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ninjax.weather.R

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    isEnableAnim: Boolean = true,
    addBackStack: Boolean = true
) {
    supportFragmentManager.beginTransaction().apply {
        if (isEnableAnim) {
            setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right
            )
        }
        replace(R.id.container, fragment, fragment.javaClass.simpleName)
        if (addBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}
