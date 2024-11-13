package com.softdevelopers.techbridge_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EmployeeItem(
    employee_id: String,
    nombres: String,
    apellidos: String,
    total_horas: Int
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2A38)),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = randomImage),
                contentDescription = "Empleado",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4CAF50).copy(alpha = 0.2f)) // Fondo semitransparente
                    .border(3.dp, Color(0xFF4CAF50), CircleShape)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = nombres.uppercase(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )

                Text(
                    text = "ID: $employee_id",
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(offset = Offset(2f, 2f), blurRadius = 5f)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Apellidos: $apellidos",
                    fontSize = 14.sp,
                    color = Color(0xFFD0D0D0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Horas Totales: $total_horas",
                    fontSize = 16.sp,
                    color = if (total_horas > 40) Color(0xFF4CAF50) else Color(0xFFE53935),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
            }
        }
    }
}
