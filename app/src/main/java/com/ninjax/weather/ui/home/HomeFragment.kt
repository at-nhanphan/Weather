package com.ninjax.weather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun initEvents() {
        btnNext.setOnClickListener {
            activity?.replaceFragment(DetailFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.weatherInfo.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    // handle api success
                    tvWeather.text = result.value.name
                }
                is ResultWrapper.NetworkError -> handleNetworkError()
                is ResultWrapper.GenericError -> handleGenericError(result.code, result.msg)
            }
        })
    }
}
