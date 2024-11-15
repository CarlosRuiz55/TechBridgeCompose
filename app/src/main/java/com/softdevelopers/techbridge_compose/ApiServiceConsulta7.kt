package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta7 {
    @GET("ganancia-&pagos-anio")
    suspend fun getProyectos(): Response<List<Consulta7>>
}