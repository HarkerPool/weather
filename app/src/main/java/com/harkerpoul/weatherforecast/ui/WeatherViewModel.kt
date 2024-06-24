package com.harkerpoul.weatherforecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harkerpoul.weatherforecast.common.BaseResult
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weatherForecast = MutableLiveData<BaseResult<List<Weather>>>()
    val weatherForecast: LiveData<BaseResult<List<Weather>>> = _weatherForecast

    fun getWeatherForecast(cityName: String, units: String, numberOfDays: Int) {
        _weatherForecast.postValue(BaseResult.Loading)

        viewModelScope.launch {
            try {
                val weatherList =
                    weatherRepository.getWeatherForecastDaily(cityName, units, numberOfDays)
                _weatherForecast.postValue(BaseResult.Success(data = weatherList))
            } catch (e: Exception) {
                _weatherForecast.postValue(BaseResult.Error(errorMessage = e.message))
            }
        }
    }
}