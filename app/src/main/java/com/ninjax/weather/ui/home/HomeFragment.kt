package com.ninjax.weather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ninjax.weather.R
import com.ninjax.weather.data.source.remote.ResultWrapper
import com.ninjax.weather.extension.replaceFragment
import com.ninjax.weather.ui.base.BaseFragment
import com.ninjax.weather.ui.detail.DetailFragment
import com.ninjax.weather.util.EventObserver
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init events
        initEvents()
        // call api
        viewModel.callApiWeather()
    }

    private fun initEvents() {
        btnNext.setOnClickListener {
            activity?.replaceFragment(DetailFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // handle API exception
        viewModel.getApiException().observe(viewLifecycleOwner, EventObserver { msg ->
            handleGenericError(msg)
        })
        // handle loading state
        viewModel.getLoadingApiException().observe(viewLifecycleOwner, EventObserver { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        // handle result from API
        viewModel.getWeatherResult().observe(viewLifecycleOwner, Observer { result ->
            if (result is ResultWrapper.Success) {
                tvWeather.text = result.value.name
            }
        })
    }
}
