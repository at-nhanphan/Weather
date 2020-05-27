package com.ninjax.weather.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninjax.weather.R
import com.ninjax.weather.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailViewModel>() {
    override fun viewModel(): DetailViewModel = viewModel<DetailViewModel>().value

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_detail_fragment, container, false)
    }
}
