package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectItem6(
    Calendar_Hierarchy: String,
    Year: Int,
    Sum_of_Profit_Margin_Without_Nulls: Int
) {
    // Seleccionar la imagen basada en el trimestre
    val specificImage = when (Calendar_Hierarchy) {
        "1" -> R.drawable.icon1
        "2" -> R.drawable.icon2
        "3" -> R.drawable.icon3
        "4" -> R.drawable.icon4
        else -> R.drawable.iconconsulta3 // Imagen predeterminada en caso de error
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = specificImage),
                contentDescription = "Trimestre",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Trimestre: $Calendar_Hierarchy",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007AFF),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = "Año: $Year",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Ingresos: $$Sum_of_Profit_Margin_Without_Nulls",
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}
