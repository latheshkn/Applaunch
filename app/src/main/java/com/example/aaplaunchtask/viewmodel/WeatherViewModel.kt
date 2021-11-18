package com.example.aaplaunchtask.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaplaunchtask.SingleLiveEvent
import com.example.aaplaunchtask.state.WeatherState
import com.example.aaplaunchtask.webservice.BaseClient
import com.example.aaplaunchtask.webservice.ModelRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val api=BaseClient.getInstance
    private var state: WeatherState = WeatherState.Init
        set(value) {
            field = value
            publishState(state)
        }
    val stateObserver: SingleLiveEvent<WeatherState> by lazy(::SingleLiveEvent)

    private fun publishState(state:WeatherState) {
        stateObserver.setValue(state)
    }
    fun onInitialized(){
        getWeather()
    }
    private fun getWeather(){

        viewModelScope.launch {

           val weather= ModelRepository(api=api).getWeather(12.9083,77.652,"imperial","b143bb707b2ee117e62649b358207d3e")

            weather?.let {
                if (weather.isSuccessful){
                    state=WeatherState.GetWeather(weather.body()!!)
                }
            }

        }
    }
}