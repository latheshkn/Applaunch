package com.example.aaplaunchtask.state

import android.os.Bundle
import com.example.aaplaunchtask.model.WeatherMap

sealed class WeatherState {
    object Init : WeatherState()
    data class GetWeather(val data:WeatherMap) : WeatherState()
}