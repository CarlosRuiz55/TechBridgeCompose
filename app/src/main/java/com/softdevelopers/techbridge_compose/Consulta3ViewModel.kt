package com.softdevelopers.techbridge_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class Consulta3ViewModel : ViewModel() {
    var employee: List<Consulta3> by mutableStateOf(emptyList())
    var total by mutableStateOf("")

    fun fetchEmployee() {
        total = "Realizando petición"
        viewModelScope.launch {
            try {
                val response: Response<List<Consulta3>> = RetrofitInstanceConsulta3.api.getEmpleados()
                if (response.isSuccessful) {
                    employee = response.body() ?: emptyList()
                    total = "Datos cargados correctamente"
                } else {
                    total = "Error en la petición: ${response.message()}"
                }
            } catch (ex: Exception) {
                total = "Error: ${ex.message}"
            }
        }
    }
}