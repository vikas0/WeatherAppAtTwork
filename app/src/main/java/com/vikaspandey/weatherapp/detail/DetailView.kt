package com.vikaspandey.weatherapp.detail

import com.vikaspandey.weatherapp.model.WeatherInfo

interface DetailView {

fun updateWeather(weather: WeatherInfo)
fun showLoader()
fun hideLoader()
    fun ShowError(errorMsg: String)
}
