package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta4 {
    @GET("empleados/empleados-salario-2500/")
    suspend fun getEmpleados(): Response<List<Consulta4>>
}