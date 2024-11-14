package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono del proyecto
            Image(
                painter = painterResource(id = R.drawable.iconempleado),
                contentDescription = "Empleado",
                modifier = Modifier
                    .size(56.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Nombre del proyecto
                Text(
                    text = nombreProyecto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E88E5)  // Acento en azul para el nombre
                )

                Spacer(modifier = Modifier.height(6.dp))

                // ID del proyecto
                Text(
                    text = "ID: $proyectoId",
                    fontSize = 14.sp,
                    color = Color(0xFF757575)  // Gris suave para el ID
                )

                // Fechas del proyecto
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Inicio: $fechaInicio - Fin: $fechaFin",
                    fontSize = 14.sp,
                    color = Color(0xFF757575)  // Gris suave para fechas
                )

                // Margen de ganancia
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Ganancia",
                        tint = if (margenGanancia <= 10) Color.Red else Color(0xFF1E88E5),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ganancia: $margenGanancia%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (margenGanancia <= 10) Color.Red else Color(0xFF1E88E5)
                    )
                }
            }
        }
    }
}
