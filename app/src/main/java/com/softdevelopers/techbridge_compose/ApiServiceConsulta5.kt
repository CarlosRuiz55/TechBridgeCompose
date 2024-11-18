package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta5 {
    @GET("proyectos-&con-mayor-ganancia")
    suspend fun getProyectos(): Response<List<Consulta5>>
}