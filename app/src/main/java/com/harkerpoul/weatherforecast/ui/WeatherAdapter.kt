package com.harkerpoul.weatherforecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harkerpoul.weatherforecast.R
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.databinding.LayoutWeatherItemBinding

class WeatherAdapter : ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean =
                oldItem.date == newItem.date
        }
    }

    inner class WeatherViewHolder(private val binding: LayoutWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.tvDate.apply {
                text = context.getString(R.string.date, weather.date)
            }
            binding.tvTemperature.apply {
                text = context.getString(R.string.average_temperature, weather.averageTemperature)
            }
            binding.tvPressure.apply {
                text = context.getString(R.string.pressure, weather.pressure)
            }
            binding.tvHumidity.apply {
                text = context.getString(R.string.humidity, "${weather.humidity}%")
            }
            binding.tvDescription.apply {
                text = context.getString(R.string.description, weather.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            LayoutWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}