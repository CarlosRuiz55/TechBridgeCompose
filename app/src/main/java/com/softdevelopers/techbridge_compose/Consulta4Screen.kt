package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Consulta4Screen(viewModel: Consulta4ViewModel = viewModel()) {
    // Llama a `fetchEmployee` solo una vez cuando se crea la composición
    LaunchedEffect(Unit) {
        viewModel.fetchEmployee()
    }

    val employee = viewModel.employee
    val total = viewModel.total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Empleados con salario superior a 2500 y más de 20 tareas completadas en proyectos",
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
        if (employee.isEmpty()) {
            Text(
                text = "Cargando...",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            // Mostrar la lista de empleados una vez que `employee` tenga datos
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(employee) { emp ->
                    EmployeeItem2(
                        employee_id = emp.employee_id,
                        nombres = emp.nombres,
                        apellidos = emp.apellidos,
                        salario = emp.salario,
                        participacion_proyectos = emp.participacion_proyectos // Pasa la lista aquí
                    )
                }
            }
        }
    }
}

