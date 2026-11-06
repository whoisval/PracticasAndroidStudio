package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.Components.CabeceraPractica

enum class CategoriaGasto { COMIDA, TRANSPORTE, RENTA, OCIO, OTROS }
data class Gasto(val id: Int, val concepto: String, val monto: Double, val categoria: CategoriaGasto)

@Composable
fun Practica09PresupuestoInicio(modifier: Modifier = Modifier) {
    var ingresoTexto by remember { mutableStateOf("4500") }
    val gastos = remember { mutableStateListOf<Gasto>() }

    var concepto by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf(CategoriaGasto.COMIDA) }

    val ingreso = ingresoTexto.toDoubleOrNull() ?: 0.0
    val total = gastos.sumOf { it.monto }
    val saldo = ingreso - total
    val porcentaje = if (ingreso > 0) total / ingreso else 0.0

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFF1F5F7)).verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CabeceraPractica("Finanzas personales", "Controla tu mes", Color(0xFF287A76), "$")

        OutlinedTextField(
            value = ingresoTexto,
            onValueChange = { ingresoTexto = it },
            label = { Text("Ingreso mensual") },
            prefix = { Text("$ ") },
            modifier = Modifier.fillMaxWidth()
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4C7)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Gastado", color = Color.Gray)
                        Text("$${"%.0f".format(total)}", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text("Saldo", color = Color.Gray)
                        Text("$${"%.0f".format(saldo)}", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { porcentaje.toFloat().coerceIn(0f, 1f) },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("${(porcentaje * 100).toInt()} % usado", color = Color.Gray)
            }
        }

        if (porcentaje >= .8) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (porcentaje > 1) Color(0xFFFFE2E2) else Color(0xFFFFF1C9)
                )
            ) {
                Text(
                    if (porcentaje > 1)
                        "⚠ Presupuesto excedido"
                    else
                        "⚠ Cuidado: pasaste el 80 %",
                    modifier = Modifier.padding(12.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text("AGREGA UN MOVIMIENTO", fontWeight = FontWeight.Bold, color = Color(0xFF287A76))

        OutlinedTextField(
            value = concepto,
            onValueChange = { concepto = it },
            label = { Text("Concepto") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = monto,
                onValueChange = { monto = it },
                label = { Text("Monto") },
                modifier = Modifier.weight(1f)
            )

            FilterChip(
                selected = true,
                onClick = {
                    categoria = CategoriaGasto.entries[
                        (categoria.ordinal + 1) % CategoriaGasto.entries.size
                    ]
                },
                label = { Text(categoria.name) }
            )
        }

        Button(
            onClick = {
                val numero = monto.toDoubleOrNull()
                if (concepto.isNotBlank() && numero != null && numero > 0) {
                    gastos.add(
                        Gasto(
                            id = (gastos.maxOfOrNull { it.id } ?: 0) + 1,
                            concepto = concepto,
                            monto = numero,
                            categoria = categoria
                        )
                    )
                    concepto = ""
                    monto = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) { Text("Agregar gasto") }

        Text("HISTORIAL DE GASTOS", fontWeight = FontWeight.Bold, color = Color(0xFF287A76))

        gastos.forEach { gasto ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(gasto.concepto, fontWeight = FontWeight.Bold)
                    Text(gasto.categoria.name, color = Color.Gray, fontSize = 11.sp)
                }
                Row {
                    Text("-$${"%.0f".format(gasto.monto)}")
                    TextButton(onClick = { gastos.remove(gasto) }) { Text("×") }
                }
            }
            HorizontalDivider()
        }
    }
}
