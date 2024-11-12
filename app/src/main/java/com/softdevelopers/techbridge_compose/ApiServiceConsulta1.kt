package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta1 {
    @GET("proyectos/proyectos-mas-caros-2024-2025/")
    suspend fun getProjects(): Response<List<Consulta1>>
}