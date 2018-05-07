package com.vikaspandey.weatherapp.detail

import com.vikaspandey.weatherapp.model.WeatherInfo
import com.vikaspandey.weatherapp.network.ApiListner
import com.vikaspandey.weatherapp.network.WeatherService


class DetailPresenter(
        val view: DetailView,
        private val volleyService: WeatherService
//        private val processScheduler: Scheduler = Schedulers.io(),
//        private val androidScheduler: Scheduler = AndroidSchedulers.mainThread())
) {

    fun fetchWeather(cityName:String) {
        view.showLoader()

        volleyService.fetchWeather(cityName, object : ApiListner {
            override fun onSuccess(weatherInfo: WeatherInfo) {
                view.updateWeather(weatherInfo)
                view.hideLoader();
            }

            override fun onError(errorMsg: String) {
                view.ShowError(errorMsg);
                view.hideLoader()
            }

        });
    }
}