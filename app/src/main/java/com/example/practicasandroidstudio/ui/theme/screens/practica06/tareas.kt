
package com.example.practicasandroidstudio.ui.theme.screens.practica06

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.Components.CabeceraPractica
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

enum class Prioridad { ALTA, MEDIA, BAJA }
enum class FiltroTarea { TODAS, PENDIENTES, HECHAS }

data class Tarea(
    val id: Int,
    val titulo: String,
    val prioridad: Prioridad,
    val hecha: Boolean = false
)

@Composable
fun Practica06TareasInicio(modifier: Modifier = Modifier) {

    val tareas = remember {
        mutableStateListOf<Tarea>()
    }

    var nueva by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf(Prioridad.ALTA) }
    var filtro by remember { mutableStateOf(FiltroTarea.TODAS) }
    var dialogo by remember { mutableStateOf(false) }

    val visibles = when (filtro) {
        FiltroTarea.TODAS -> tareas
        FiltroTarea.PENDIENTES -> tareas.filter { !it.hecha }
        FiltroTarea.HECHAS -> tareas.filter { it.hecha }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F7))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        CabeceraPractica(
            "Organiza tu semana",
            "Un pendiente a la vez",
            Color(0xFF7955AD)
        )

        // AGREGAR NUEVA TAREA
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nueva,
                onValueChange = { nueva = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    if (nueva.isNotBlank()) {
                        tareas.add(
                            Tarea(
                                (tareas.maxOfOrNull { it.id } ?: 0) + 1,
                                nueva.trim(),
                                prioridad
                            )
                        )
                        nueva = ""
                    }
                }
            ) {
                Text("+")
            }
        }

        // RESUMEN DE TAREAS
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Pendientes",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "${tareas.count { !it.hecha }}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7955AD)
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Completadas",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "${tareas.count { it.hecha }}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF248872)
                    )
                }
            }
        }

        // PRIORIDAD DE LA TAREA
        Text(
            "Prioridad de la tarea",
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Prioridad.entries.forEach {
                FilterChip(
                    selected = prioridad == it,
                    onClick = { prioridad = it },
                    label = {
                        Text(
                            it.name.lowercase()
                                .replaceFirstChar { c -> c.uppercase() }
                        )
                    }
                )
            }
        }

        // FILTROS
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FiltroTarea.entries.forEach {
                FilterChip(
                    selected = filtro == it,
                    onClick = { filtro = it },
                    label = {
                        Text(
                            it.name.lowercase()
                                .replaceFirstChar { c -> c.uppercase() }
                        )
                    }
                )
            }
        }

        // LISTA DE TAREAS
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(visibles, key = { it.id }) { tarea ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Checkbox(
                        checked = tarea.hecha,
                        onCheckedChange = { nuevo ->
                            val i = tareas.indexOfFirst {
                                it.id == tarea.id
                            }

                            if (i >= 0) {
                                tareas[i] = tareas[i].copy(
                                    hecha = nuevo
                                )
                            }
                        }
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = tarea.titulo,
                            textDecoration = if (tarea.hecha)
                                TextDecoration.LineThrough
                            else
                                null
                        )

                        Text(
                            text = tarea.prioridad.name,
                            color = when (tarea.prioridad) {
                                Prioridad.ALTA -> Color(0xFFD32F2F)
                                Prioridad.MEDIA -> Color(0xFFF9A825)
                                Prioridad.BAJA -> Color(0xFF248872)
                            },
                            fontSize = 11.sp
                        )
                    }

                    TextButton(
                        onClick = {
                            tareas.removeAll {
                                it.id == tarea.id
                            }
                        }
                    ) {
                        Text("×")
                    }
                }

                HorizontalDivider()
            }
        }

        // TOTAL DE PENDIENTES
        Text(
            text = "${tareas.count { !it.hecha }} pendientes de ${tareas.size}",
            fontWeight = FontWeight.Bold
        )

        // BORRAR COMPLETADAS
        OutlinedButton(
            onClick = { dialogo = true },
            enabled = tareas.any { it.hecha },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Borrar completadas")
        }
    }

    // DIÁLOGO DE CONFIRMACIÓN
    if (dialogo) {
        AlertDialog(
            onDismissRequest = {
                dialogo = false
            },
            title = {
                Text("Borrar completadas")
            },
            text = {
                Text(
                    "¿Deseas eliminar todas las tareas completadas?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tareas.removeAll { it.hecha }
                        dialogo = false
                    }
                ) {
                    Text("Borrar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogo = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Practica06TareasPreview() {
    PracticasAndroidStudioTheme {
        Practica06TareasInicio()
    }
}
