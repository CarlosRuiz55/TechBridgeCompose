package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta2 {
    private const val BASE_URL = "https://5a17-2803-2d60-1105-2873-5101-75c7-e41a-a148.ngrok-free.app/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta2 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta2::class.java)
    }
}