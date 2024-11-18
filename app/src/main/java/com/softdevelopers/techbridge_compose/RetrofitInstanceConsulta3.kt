package com.softdevelopers.techbridge_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceConsulta3 {
    private const val BASE_URL = "http://containermongotechbridge.f8g3defvenehfmeq.mexicocentral.azurecontainer.io:8000/" // Cambia esto por la URL base de tu API
    //private const val BASE_URL = " https://b791-2803-2d60-1105-2873-4dae-fd1-5046-18b1.ngrok-free.app/"

    val api: ApiServiceConsulta3 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceConsulta3::class.java)
    }
}