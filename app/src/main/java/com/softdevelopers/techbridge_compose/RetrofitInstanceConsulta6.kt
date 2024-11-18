package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta6 {
    private const val BASE_URL = "https://apicubetechbridge.canadacentral.cloudapp.azure.com/apicube-tabular/" // Cambia esto por la URL base de tu API

    val api: ApiServiceConsulta6 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta6::class.java)
    }
}