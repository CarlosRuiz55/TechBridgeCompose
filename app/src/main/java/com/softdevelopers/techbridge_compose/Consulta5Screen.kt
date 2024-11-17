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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Consulta5Screen(viewModel: Consulta5ViewModel = viewModel(), onBack: () -> Unit) {
    // Llama a `fetchProyecto` solo una vez cuando se crea la composición
    LaunchedEffect(Unit) {
        viewModel.fetchProyecto()
    }

    val proyecto = viewModel.proyecto
    val total = viewModel.total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable {onBack()}
        )
        Text(
            text = "10 proyectos con mayor ganancia",
            fontSize = 18.sp,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
                .offset(y = 0.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Condicional para mostrar mensaje mientras se cargan los datos
        if (proyecto.isEmpty()) {
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
                items(proyecto) { p ->
                    ProjectItem5(
                        project_name = p.project_name,
                        sum_of_payment_amount_without_nulls = p.sum_of_payment_amount_without_nulls,
                        sum_of_Profit_Margin_Without_Nulls = p.sum_of_Profit_Margin_Without_Nulls,
                        count_of_payment_amount_without_nulls = p.count_of_payment_amount_without_nulls,
                    )
                }
            }
        }
    }
}
