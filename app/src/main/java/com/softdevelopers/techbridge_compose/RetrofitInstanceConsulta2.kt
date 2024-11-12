package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta2 {
    private const val BASE_URL = "https://0724-2803-2d60-1105-2873-e451-bce4-6c60-e7e8.ngrok-free.app/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta2 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta2::class.java)
    }
}