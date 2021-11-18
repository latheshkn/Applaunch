package com.example.aaplaunchtask.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseClient {

    private val BaseUrl: String = "https://api.openweathermap.org/data/2.5/"
    public val getInstance: Api by lazy {

        val retrofit =
            Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(Api::class.java)
    }

}