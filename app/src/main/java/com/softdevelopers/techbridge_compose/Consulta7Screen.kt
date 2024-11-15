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
fun Consulta7Screen(viewModel: Consulta7ViewModel = viewModel(), onBack: () -> Unit) {
    // Llama a `fetchProyectos` solo una vez cuando se crea la composición
    LaunchedEffect(Unit) {
        viewModel.fetchProyecto()
    }

    val proyecto = viewModel.proyecto
    val total = viewModel.total

    var selectedYear by remember { mutableStateOf(2000) }
    val years = (2005..2024).toList()

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
                .clickable { onBack() }
        )
        Text(
            text = "Total de ganancia y cantidad de pagos realizados",
            fontSize = 18.sp,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
                .offset(y = 0.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // ComboBox for selecting year
        DropdownMenu(
            modifier = Modifier.padding(16.dp),
            selectedYear = selectedYear,
            onYearSelected = { selectedYear = it },
            years = years
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Condicional para mostrar mensaje mientras se cargan los datos
        if (proyecto.isEmpty()) {
            Text(
                text = "Cargando...",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            val filteredProjects = proyecto.filter { it.Year == selectedYear }

            // Mostrar la lista de proyectos filtrados
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredProjects) { proye ->
                    ProjectItem7(
                        Calendar_Hierarchy = proye.Calendar_Hierarchy,
                        Year = proye.Year,
                        Sum_of_Profit_Margin_Without_Nulls = proye.Sum_of_Profit_Margin_Without_Nulls,
                        Count_of_Payment_Amount_Without_Nulls = proye.Count_of_Payment_Amount_Without_Nulls
                    )
                }
            }
        }
    }
}
