package com.example.practicasandroidstudio.ui.theme.screens.practica05

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.Components.CabeceraPractica
import kotlin.math.ceil

@Composable
fun Practica05CuentaInicio(modifier: Modifier = Modifier) {
    var montoTexto by remember { mutableStateOf("") }
    var propinaPct by remember { mutableStateOf(15) }
    var usaOtro by remember { mutableStateOf(false) }
    var personas by remember { mutableStateOf(2) }
    var redondear by remember { mutableStateOf(false) }

    val monto = montoTexto.toDoubleOrNull() ?: 0.0
    val propina = monto * propinaPct / 100.0
    val total = monto + propina
    val base = if (personas > 0) total / personas else 0.0
    val porPersona = if (redondear) ceil(base) else base
    val sobrante = porPersona * personas - total

    Column(
        modifier = modifier.fillMaxSize()
            .background(Color(0xFFF1F5F7))
            .verticalScroll(rememberScrollState())
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        CabeceraPractica("Dividamos la cuenta", "Cada quien paga lo justo",
        Color(0xFF237AB9))

        OutlinedTextField(
            value = montoTexto,
            onValueChange = { montoTexto = it },
            label = { Text("Monto de la cuenta") },
            prefix = { Text("$ ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        if (montoTexto.isNotEmpty() && montoTexto.toDoubleOrNull() == null) {
            Text("Introdoce un monto válido", color = Color(0xFFC04F35))
        }
        Text("¿Cuánta propina dejamos?", fontWeight = FontWeight.Bold, color = Color(0xFF237AB9))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf(10, 15, 20).forEach {
                FilterChip(
                    selected = !usaOtro && propinaPct == it,
                    onClick = {
                        propinaPct = it
                        usaOtro = false
                    },
                    label = { Text("$it %") }
                )
            }
            FilterChip(
                selected = usaOtro,
                onClick = { usaOtro = true },
                label = { Text("Otro") }
            )
        }

        if (usaOtro) {
            Slider(
                value = propinaPct.toFloat(),
                onValueChange = { propinaPct = it.toInt() },
                valueRange = 0f..30f
            )
        }

        Row {
            Text("Personas", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Button(onClick = { if (personas > 1) personas-- }) { Text("-") }
            Text("$personas", modifier = Modifier.padding(12.dp))
            Button(onClick = { if (personas < 20) personas++ }) { Text("+") }
        }

        Row {
            Text("Redondear por persona", modifier = Modifier.weight(1f))
            Switch(checked = redondear, onCheckedChange = { redondear = it })
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Propina ($propinaPct %)"); Text("$${"%.2f".format(propina)}")
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total con propina"); Text("$${"%.2f".format(total)}")
                }

                HorizontalDivider()

                Text("Importe por persona", color = Color(0xFF237AB9), fontWeight = FontWeight.Bold)
                Text(
                    "$${"%.2f".format(porPersona)}",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text("entre $personas personas", color = Color.Gray)
                if (redondear) Text("Sobrante por redondeo: $${"%.2f".format(sobrante)}", fontSize = 12.sp)

            }
        }
    }
}