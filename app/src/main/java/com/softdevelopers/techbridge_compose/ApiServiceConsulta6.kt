package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta6 {
    @GET("dinero-ganado-&trimestre")
    suspend fun getProyectos(): Response<List<Consulta6>>
}