package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Consulta1Screen(viewModel: Consulta1ViewModel = viewModel()) {
    // Llama a `fetchProjects` solo una vez cuando se crea la composición
    LaunchedEffect(Unit) {
        viewModel.fetchProjects()
    }

    val projects = viewModel.projects
    val total = viewModel.total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Top 5 Proyectos Más Caros 2024-2025 por Margen de Ganancia",
            fontSize = 18.sp,
            color = Color(0xFF007AFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        // Condicional para mostrar mensaje mientras se cargan los datos
        if (projects.isEmpty()) {
            Text(
                text = "Cargando...",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            // Mostrar la lista de proyectos una vez que `projects` tenga datos
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(projects) { project ->
                    ProjectItem(
                        proyectoId = project.proyecto_id,
                        nombreProyecto = project.nombre_proyecto,
                        fechaInicio = project.fecha_inicio,
                        fechaFin = project.fecha_fin,
                        margenGanancia = project.margen_ganancia
                    )
                }
            }
        }
    }
}
