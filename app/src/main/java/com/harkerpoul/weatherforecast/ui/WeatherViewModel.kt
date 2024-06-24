package com.harkerpoul.weatherforecast.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harkerpoul.weatherforecast.common.BaseResult
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    /**
     * Coroutine Exception Handler
     */
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _weatherForecast = MutableLiveData<BaseResult<List<Weather>>>()
    val weatherForecast: LiveData<BaseResult<List<Weather>>> = _weatherForecast

    fun getWeatherForecast(cityName: String, numberOfDays: Int) {
        _weatherForecast.postValue(BaseResult.Loading)

        val job = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val weatherList = weatherRepository.getWeatherForecastDaily(cityName, numberOfDays)
                _weatherForecast.postValue(BaseResult.Success(data = weatherList))
            } catch (e: Exception) {
                _weatherForecast.postValue(BaseResult.Error(errorMessage = e.message))
            }
        }

        job.invokeOnCompletion {
            Log.e("WeatherViewModel", it?.message ?: "invokeOnCompletion")
        }
    }
}