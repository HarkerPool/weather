package com.harkerpoul.weatherforecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harkerpoul.weatherforecast.R
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.databinding.LayoutWeatherItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherAdapter : ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean =
                oldItem.dt == newItem.dt
        }
    }

    inner class WeatherViewHolder(private val binding: LayoutWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.tvDate.apply {
                val format = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
                val date = format.format(weather.dt * 1000L)
                text = context.getString(R.string.date, date)
            }
            binding.tvTemperature.apply {
                val averageTemperature = ((weather.temp.min + weather.temp.max) / 2).toInt()
                text = context.getString(R.string.average_temperature, averageTemperature)
            }
            binding.tvPressure.apply {
                text = context.getString(R.string.pressure, weather.pressure)
            }
            binding.tvHumidity.apply {
                text = context.getString(R.string.humidity, "${weather.humidity}%")
            }
            binding.tvDescription.apply {
                text = context.getString(R.string.description, weather.weather[0].description)
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