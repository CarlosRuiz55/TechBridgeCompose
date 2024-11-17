package com.softdevelopers.techbridge_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectItem5(
    project_name: String,
    sum_of_payment_amount_without_nulls: Int,
    sum_of_Profit_Margin_Without_Nulls: Int,
    count_of_payment_amount_without_nulls: Int
) {
    // Cálculo del margen de ganancia en porcentaje
    val profitMarginPercentage = if (sum_of_payment_amount_without_nulls != 0) {
        (sum_of_Profit_Margin_Without_Nulls.toDouble() / sum_of_payment_amount_without_nulls * 100).toInt()
    } else {
        0
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
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Nombre del proyecto: $project_name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007AFF),
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                // Barra de Progreso del Margen de Ganancia
                Text(
                    text = "Margen de ganancia: $profitMarginPercentage%",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                androidx.compose.material.LinearProgressIndicator(
                    progress = profitMarginPercentage / 100f,
                    color = Color(0xFF4CAF50),
                    backgroundColor = Color(0xFFE0E0E0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 12.dp)
                )

                Text(
                    text = "Cantidad de pagos: $count_of_payment_amount_without_nulls",
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}

