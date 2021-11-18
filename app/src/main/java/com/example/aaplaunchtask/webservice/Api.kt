package com.example.aaplaunchtask.webservice

import com.example.aaplaunchtask.model.WeatherMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("onecall")
    suspend fun getWeatherMap(
        @Query("lat") lat: Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") appid:String,
    ): Response<WeatherMap>

}