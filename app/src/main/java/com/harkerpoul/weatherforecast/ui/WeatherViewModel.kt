package com.harkerpoul.weatherforecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harkerpoul.weatherforecast.common.BaseResult
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherForecast = MutableLiveData<BaseResult<List<Weather>>>()
    val weatherForecast: LiveData<BaseResult<List<Weather>>> = _weatherForecast

    fun getWeatherForecast(cityName: String, numberOfDays: Int) {
        _weatherForecast.value = BaseResult.Loading

        val weatherList = mutableListOf<Weather>()

        for (i in 0..6) {
            val weather = Weather(
                dt = 1719201600,
                averageTemperature = (10..30).random(),
                pressure = (1000..1010).random(),
                humidity = (50..80).random(),
                description = "Sunny"
            )
            weatherList.add(weather)
        }

        _weatherForecast.value = BaseResult.Success(weatherList)
    }
}