package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta4{
    private const val BASE_URL = "https://27e6-2803-2d60-1105-2873-382c-15b6-e4fb-897f.ngrok-free.app/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta4 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta4::class.java)
    }
}