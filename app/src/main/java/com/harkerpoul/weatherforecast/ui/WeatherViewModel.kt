package com.harkerpoul.weatherforecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harkerpoul.weatherforecast.common.BaseResult
import com.harkerpoul.weatherforecast.data.model.Weather
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherViewModel : ViewModel() {

    private val _weatherForecast = MutableLiveData<BaseResult<List<Weather>>>()
    val weatherForecast: LiveData<BaseResult<List<Weather>>> = _weatherForecast
    fun getWeatherForecast(cityName: String, numberOfDays: Int) {
        _weatherForecast.value = BaseResult.Loading

        val weatherList = mutableListOf<Weather>()
        val format = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
        for (i in 0..6) {
            val date = format.format(1719201600 * 1000L)
            val weather = Weather(
                date = date,
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