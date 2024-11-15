package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta7 {
    private const val BASE_URL = "https://f83d-2803-2d60-1105-2873-d924-164-a08e-31f4.ngrok-free.app/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta7 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta7::class.java)
    }
}