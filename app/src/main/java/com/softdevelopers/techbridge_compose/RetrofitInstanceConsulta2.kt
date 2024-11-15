package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta2 {
    private const val BASE_URL = "http://containermongotechbridge.f8g3defvenehfmeq.mexicocentral.azurecontainer.io:8000/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta2 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta2::class.java)
    }
}