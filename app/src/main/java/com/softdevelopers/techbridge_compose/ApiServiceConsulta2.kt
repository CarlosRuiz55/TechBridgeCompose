package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta2 {
    @GET("proyectos/proyectos-finalizados-ganancia-10000/")
    suspend fun getProjects(): Response<List<Consulta2>>
}