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

@Composable
fun Practica08ResenaInicio(modifier: Modifier = Modifier) {
    var comida by remember { mutableStateOf(0) }
    var servicio by remember { mutableStateOf(0) }
    var ambiente by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }
    var etiquetas by remember { mutableStateOf(emptySet<String>()) }

    val general = (comida + servicio + ambiente) / 3.0
    var publicada by remember { mutableStateOf(false) }
    val puedePublicar = comida > 0 && servicio > 0 && ambiente > 0 && comentario.length >= 20

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFF1F5F7)).verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CabeceraPractica("Tu opinión importa", "Comparte una experiencia", Color(0xFFD18B25))
        Text("VALORACIÓN", fontWeight = FontWeight.Bold, color = Color(0xFFD18B25))
        Text("Puntúa comida, servicio y ambiente", color = Color.Gray)

        RatingFila("Comida", comida) { comida = it }
        RatingFila("Servicio", servicio) { servicio = it }
        RatingFila("Ambiente", ambiente) { ambiente = it }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1ECF8))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Calificación general")
                Text("${"%.1f".format(general)} ★", fontWeight = FontWeight.Bold)
            }
        }

        OutlinedTextField(
            value = comentario,
            onValueChange = { if (it.length <= 200) comentario = it },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            supportingText = {
                Text("${comentario.length}/200")
            }
        )

        val opciones = listOf("Familiar", "Romántico", "Económico", "Vegetariano", "Rápido")

        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            opciones.forEach { etiqueta ->
                FilterChip(
                    selected = etiqueta in etiquetas,
                    onClick = {
                        etiquetas = if (etiqueta in etiquetas)
                            etiquetas - etiqueta
                        else
                            etiquetas + etiqueta
                    },
                    label = { Text(etiqueta) }
                )
            }
        }

        Button(
            onClick = { publicada = true },
            enabled = puedePublicar,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) { Text("Publicar") }
        if (publicada) {
            Card {
                Column(Modifier.padding(14.dp)) {
                    Text("¡Reseña publicada!", fontWeight = FontWeight.Bold)
                    Text("Promedio: ${"%.1f".format(general)} / 5")
                    Text(comentario)
                }
            }
        }
    }
}

@Composable
fun RatingFila(titulo: String, valor: Int, onCambio: (Int) -> Unit) {
    Column {
        Text(titulo, fontWeight = FontWeight.SemiBold)
        Row {
            (1..5).forEach { estrella ->
                TextButton(onClick = { onCambio(estrella) }) {
                    Text(
                        if (estrella <= valor) "★" else "☆",
                        fontSize = 26.sp,
                        color = Color(0xFFFFB000)
                    )
                }
            }
        }
    }
}
