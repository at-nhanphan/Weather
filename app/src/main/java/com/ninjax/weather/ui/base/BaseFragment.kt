package com.ninjax.weather.ui.base

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun handleNetworkError() {
        val dialog = AlertDialog.Builder(this.requireContext())
            .setMessage("Network Error")
            .create()
        dialog.show()
    }

    protected fun handleGenericError(code: Int?, msg: String?) {
//        when (code) {
//            HttpURLConnection.HTTP_NOT_FOUND -> {
        AlertDialog.Builder(this.requireContext())
            .setMessage("Notfound Error")
            .create()
            .show()
//            }
//        }
    }
}
