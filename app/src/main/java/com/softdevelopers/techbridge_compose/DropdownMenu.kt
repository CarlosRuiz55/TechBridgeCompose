package com.softdevelopers.techbridge_compose

import androidx.compose.material3.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DropdownMenu(
    modifier: Modifier = Modifier,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    years: List<Int>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Text(
            text = "Seleccionar Año: $selectedYear",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
        ) {
            years.forEach { year ->
                DropdownMenuItem(
                    text = { Text("$year", style = MaterialTheme.typography.labelMedium.copy(color = Color.Blue)) },
                    onClick = {
                        onYearSelected(year)
                        expanded = false
                    },
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}
@Composable
fun QuarterDropdownMenu(
    modifier: Modifier = Modifier,
    selectedQuarter: Int,
    onQuarterSelected: (Int) -> Unit,
    quarters: List<Int>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Text(
            text = "Seleccionar Trimestre: Q$selectedQuarter",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        androidx.compose.material3.DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
        ) {
            quarters.forEach { quarter ->
                DropdownMenuItem(
                    text = { Text("Q$quarter", style = MaterialTheme.typography.labelMedium.copy(color = Color.Blue)) },
                    onClick = {
                        onQuarterSelected(quarter)
                        expanded = false
                    },
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}
