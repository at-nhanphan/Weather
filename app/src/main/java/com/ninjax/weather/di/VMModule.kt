package com.ninjax.weather.di

import com.ninjax.weather.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashVM = module {

}

val homeVM = module {
    viewModel { HomeViewModel() }
}
