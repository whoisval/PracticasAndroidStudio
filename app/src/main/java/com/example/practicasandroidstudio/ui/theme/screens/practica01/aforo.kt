package com.example.practicasandroidstudio.ui.theme.screens.practica01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Practica01AforoInicio(modifier: Modifier = Modifier) {
    val maximo = 50
    var actuales by remember { mutableStateOf(32) }
    var visitas by remember { mutableStateOf(118) }

    val porcentaje = actuales / maximo.toFloat()
    val colorEstado = when {
        porcentaje >= 1f -> Color(0xFFD32f2f)
        porcentaje >= .70f -> Color(0xFFF9A825)
        else -> Color(0xFF2E7D32)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6FB))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Biblioteca Central", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Sala de estudio - capacidad 50", color = Color.Gray)

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE7F6E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("$actuales / $maximo", fontSize = 46.sp, fontWeight = FontWeight.Bold, color = colorEstado)
                Text("personas en la sala", color = Color.Gray)

                Spacer(Modifier.height(18.dp))

                LinearProgressIndicator(
                    progress = { porcentaje.coerceIn(0f, 1f) },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = colorEstado
                )

                Spacer(Modifier.height(10.dp))
                Text("${(porcentaje * 100).toInt()} % de ocupación", fontWeight = FontWeight.Bold)
                Text(
                    when {
                        porcentaje >= 1f -> "Aforo completo"
                        porcentaje >= .70f -> "Casi lleno"
                        else -> "Aforo disponible"
                    },
                    color = colorEstado
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(
                onClick = { if (actuales > 0) actuales-- },
                enabled = actuales > 0,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp)
            ) { Text("- Sale") }

            Button(
                onClick = {
                    if (actuales < maximo) {
                        actuales++
                        visitas++
                    }
                },
                enabled = actuales < maximo,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp)
            ) { Text("+ Entra") }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(contentColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Visitas del día", fontWeight = FontWeight.Bold)
                    Text("Solo sumas las entradas", color = Color.Gray, fontSize = 12.sp)
                }
                Text("$visitas", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.weight(1f))

        OutlinedButton(
            onClick = {
                actuales = 0
                visitas = 0
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Cerrar día")
        }
    }
}