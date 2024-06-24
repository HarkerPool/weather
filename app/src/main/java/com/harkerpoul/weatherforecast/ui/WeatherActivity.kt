package com.harkerpoul.weatherforecast.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.harkerpoul.weatherforecast.R
import com.harkerpoul.weatherforecast.common.BaseResult
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.databinding.ActivityWeatherBinding
import com.harkerpoul.weatherforecast.utils.hideKeyboard

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var adapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObjects()
        setupEvents()
    }

    /**
     * Setup objects
     */
    private fun setupObjects() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        with(decoration) {
            setDrawable(
                AppCompatResources.getDrawable(
                    this@WeatherActivity,
                    R.drawable.divider_tiny
                )!!
            )
        }

        binding.rcvWeathers.addItemDecoration(decoration)
        binding.rcvWeathers.layoutManager = LinearLayoutManager(this)

        adapter = WeatherAdapter()
        binding.rcvWeathers.adapter = adapter
    }

    /**
     * Setup events
     */
    private fun setupEvents() {
        binding.edtCity.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> getWeatherForecast()
            }
            true
        }

        binding.btnGetWeather.setOnClickListener {
            getWeatherForecast()
        }

        viewModel.weatherForecast.observe(this) {
            handleWeatherForecast(it)
        }
    }

    /**
     * Validate input city name
     */
    private fun isValidate(): Boolean {
        val length = binding.edtCity.text?.length
        return length != null && length > 2
    }

    /**
     * Get weather forecast
     */
    private fun getWeatherForecast() {
        if (!isValidate()) {
            Toast.makeText(this, "City name must be more than 2 characters", Toast.LENGTH_SHORT)
                .show()
            return
        }
        hideKeyboard()
        viewModel.getWeatherForecast(binding.edtCity.text.toString(), 7)
    }

    private fun handleWeatherForecast(result: BaseResult<List<Weather>>) {
        when (result) {
            is BaseResult.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }

            is BaseResult.Success -> {
                binding.loading.visibility = View.GONE
                adapter.submitList(result.data)
            }

            is BaseResult.Error -> {
                binding.loading.visibility = View.GONE
                Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}