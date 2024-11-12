package com.softdevelopers.techbridge_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class Consulta1ViewModel : ViewModel() {
    var projects: List<Consulta1> by mutableStateOf(emptyList())
    var total by mutableStateOf("")

    fun fetchProjects() {
        total = "Realizando petición"
        viewModelScope.launch {
            try {
                val response: Response<List<Consulta1>> = RetrofitInstanceConsulta1.api.getProjects()
                if (response.isSuccessful) {
                    projects = response.body() ?: emptyList()
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
