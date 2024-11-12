package com.softdevelopers.techbridge_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProjectItem2(
    proyectoId: String,
    nombreProyecto: String,
    fechaInicio: String,
    estado: String,
) {
    // Lista de imágenes disponibles
    val imagesList = listOf(
        R.drawable.iconprojects, // Asegúrate de tener varias imágenes en tus recursos
        R.drawable.iconproject2,
        R.drawable.iconproject3,
        R.drawable.iconproject4
    )

    // Seleccionamos una imagen aleatoria de la lista
    val randomImage = imagesList.random()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E3B4E)), // Color de fondo más oscuro
        elevation = CardDefaults.cardElevation(8.dp) // Elevación más pronunciada
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono redondeado y con sombra, eligiendo la imagen aleatoria
            Image(
                painter = painterResource(id = randomImage),
                contentDescription = "Proyecto",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF4CAF50), CircleShape) // Borde con color verde
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Nombre del proyecto en negrita y más grande
                Text(
                    text = nombreProyecto.toUpperCase(),  // Convierte el texto a mayúsculas
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()  // Hace que ocupe todo el ancho disponible
                        .padding(start = 10.dp)
                )

                // Texto de la ID con un toque de sombra y color gris claro
                Text(
                    text = "ID: $proyectoId",
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0),
                    modifier = Modifier
                        .fillMaxWidth()  // Hace que ocupe todo el ancho disponible
                        .padding(start = 10.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(offset = Offset(1f, 1f), blurRadius = 4f)
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Fecha de inicio en un color más suave
                Text(
                    text = "Inicio: $fechaInicio",
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0),
                    modifier = Modifier
                        .fillMaxWidth()  // Hace que ocupe todo el ancho disponible
                        .padding(start = 10.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))

                // Estado con un color verde brillante si está activo o rojo si está inactivo
                Text(
                    text = "Estado: $estado",
                    fontSize = 16.sp,
                    color = if (estado == "Finalizado") Color(0xFF4CAF50) else Color(0xFFE53935),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()  // Hace que ocupe todo el ancho disponible
                        .padding(start = 10.dp)
                )
            }
        }
    }
}


