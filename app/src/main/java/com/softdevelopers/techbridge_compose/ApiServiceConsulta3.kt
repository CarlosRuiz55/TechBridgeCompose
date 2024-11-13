package com.softdevelopers.techbridge_compose

import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceConsulta3 {
    @GET("empleados/empleados-mas-de-100-horas-trabajadas/")
    suspend fun getEmpleados(): Response<List<Consulta3>>
}