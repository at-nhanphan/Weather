package com.ninjax.weather.ui.detail

import com.ninjax.weather.R
import com.ninjax.weather.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailViewModel>() {
    override fun viewModel(): DetailViewModel = viewModel<DetailViewModel>().value

    override fun getLayoutResource(): Int = R.layout.weather_detail_fragment
}
