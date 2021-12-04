package com.max.android_perona.di

import com.max.android_perona.view.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WeatherViewModel()
    }
}