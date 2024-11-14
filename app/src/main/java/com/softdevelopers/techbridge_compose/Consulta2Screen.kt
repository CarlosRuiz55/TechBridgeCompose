package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Consulta2Screen(viewModel: Consulta2ViewModel = viewModel(),onBack: () -> Unit) {
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
        Icon(
            painter = painterResource(id = R.drawable.iconback),
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable {onBack()}
        )
        Text(
            text = "Proyectos con un margen de ganancia mayor a 10,000 que se encuentran en estado Finalizado",
            fontSize = 18.sp,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .offset(y = 30.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

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
                    ProjectItem2(
                        proyectoId = project.proyecto_id,
                        nombreProyecto = project.nombre_proyecto,
                        fechaInicio = project.fecha_inicio,
                        estado = project.estado
                    )
                }
            }
        }
    }
}


