package com.ninjax.weather.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ninjax.weather.R
import com.ninjax.weather.extension.replaceFragment
import com.ninjax.weather.ui.base.BaseFragment
import com.ninjax.weather.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>() {
    override fun viewModel(): HomeViewModel = viewModel<HomeViewModel>().value

    override fun getLayoutResource(): Int = R.layout.home_fragment

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

        // handle result from API
        viewModel().getWeatherResult().observe(viewLifecycleOwner, Observer { result ->
            tvWeather.text = result.name
        })
    }
}
