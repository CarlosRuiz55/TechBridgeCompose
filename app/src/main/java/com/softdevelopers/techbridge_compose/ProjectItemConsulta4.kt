package com.softdevelopers.techbridge_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

@Composable
fun EmployeeItem2(
    employee_id: String,
    nombres: String,
    apellidos: String,
    salario: Int,
    participacion_proyectos: List<ParticipacionProyecto>
) {
    val imagesList = listOf(
        R.drawable.iconemployee1,
        R.drawable.iconemployee2,
        R.drawable.iconemployee3,
        R.drawable.iconemployee4
    )

    val randomImage = imagesList.random()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)), // Fondo claro

        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de perfil con bordes y fondo sutil
            Image(
                painter = painterResource(id = randomImage),
                contentDescription = "Empleado",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color(0xFF4CAF50), CircleShape)
                    .background(Color(0xFF4CAF50).copy(alpha = 0.1f))
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Nombre y Apellidos juntos (más grande y en negrita)
                Text(
                    text = "$nombres $apellidos",  // Concatenamos nombre y apellido
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                )

                // ID en formato de texto más pequeño
                Text(
                    text = "ID: $employee_id",
                    fontSize = 14.sp,
                    color = Color(0xFF888888),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                // Salario con color verde o rojo dependiendo del valor
                Text(
                    text = "Salario: $salario",
                    fontSize = 18.sp,
                    color = Color(0xFF4CAF50) ,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Sección de proyecto con información agrupada
                participacion_proyectos.firstOrNull()?.let { proyecto ->
                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                        // Título de la sección
                        Text(
                            text = "Detalles del Proyecto",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        // Información del proyecto
                        Text(
                            text = "ID Proyecto: ${proyecto.project_id}",
                            fontSize = 14.sp,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Rol: ${proyecto.rol}",
                            fontSize = 14.sp,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Horas: ${proyecto.horas}",
                            fontSize = 14.sp,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Tareas Completadas: ${proyecto.tareas_completadas}",
                            fontSize = 14.sp,
                            color = Color(0xFF007AFF),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Comentarios: ${proyecto.comentarios}",
                            fontSize = 14.sp,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
