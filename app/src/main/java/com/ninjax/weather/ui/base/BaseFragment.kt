package com.ninjax.weather.ui.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ninjax.weather.util.EventObserver
import com.ninjax.weather.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Handle Api exception
        handelApiException()
    }

    abstract fun viewModel(): VM

    open fun handelApiException() {
        viewModel().getApiException().observe(viewLifecycleOwner, EventObserver { msg ->
            handleGenericError(msg)
        })
    }

    protected fun handleNetworkError() {
        val dialog = AlertDialog.Builder(this.requireContext())
            .setMessage("Network Error")
            .create()
        dialog.show()
    }

    protected fun handleGenericError(msg: String?) {
        AlertDialog.Builder(this.requireContext())
            .setMessage(msg)
            .create()
            .show()
    }
}
