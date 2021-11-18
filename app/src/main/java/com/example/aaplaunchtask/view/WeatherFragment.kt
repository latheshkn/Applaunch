package com.example.aaplaunchtask.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.databinding.FragmentWeatherBinding
import com.example.aaplaunchtask.state.WeatherState
import com.example.aaplaunchtask.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()
    lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel.onInitialized()
        viewModel.stateObserver.observe(this, {
            when (it) {
                is WeatherState.GetWeather -> {
                    binding.progressBar.isVisible = false
                    binding.weatherContainer.isVisible = true
                    binding.temperature.text =
                        it.data.current.temp.toString() + getString(R.string.cel)
                    binding.cityHumidityNo.text =
                        it.data.current.humidity.toString()
                    binding.cityWindSpeedNo.text =
                        it.data.current.wind_speed.toString()
                    binding.cityNameNo.text =
                        it.data.current.feels_like.toString()

                }
                else -> {

                }
            }
        })
        return binding.root
    }

}