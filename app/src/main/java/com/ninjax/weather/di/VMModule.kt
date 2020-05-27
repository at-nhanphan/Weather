package com.ninjax.weather.di

import com.ninjax.weather.ui.detail.DetailViewModel
import com.ninjax.weather.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { DetailViewModel() }
}
