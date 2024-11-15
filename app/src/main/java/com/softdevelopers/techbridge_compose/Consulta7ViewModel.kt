package com.softdevelopers.techbridge_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response


class Consulta7ViewModel : ViewModel() {
    var proyecto: List<Consulta7> by mutableStateOf(emptyList())
    var total by mutableStateOf("")

    fun fetchProyecto() {
        total = "Realizando petición"
        viewModelScope.launch {
            try {
                val response: Response<List<Consulta7>> = RetrofitInstanceConsulta7.api.getProyectos()
                if (response.isSuccessful) {
                    proyecto = response.body() ?: emptyList()
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