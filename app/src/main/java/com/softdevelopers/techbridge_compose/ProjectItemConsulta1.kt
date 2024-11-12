package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectItem(
    proyectoId: String,
    nombreProyecto: String,
    fechaInicio: String,
    fechaFin: String,
    margenGanancia: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconempleado),
                contentDescription = "Empleado",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = nombreProyecto,
                    fontSize = 18.sp,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "ID: $proyectoId",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )

                Text(
                    text = "Inicio: $fechaInicio - Fin: $fechaFin",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )

                Text(
                    text = "Ganancia: $margenGanancia",
                    fontSize = 14.sp,
                    color = if (margenGanancia <= 10) Color.Red else Color.Blue
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
