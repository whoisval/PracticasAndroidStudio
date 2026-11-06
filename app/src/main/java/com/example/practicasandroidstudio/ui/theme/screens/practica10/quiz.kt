package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val correcta: Int
)

enum class PantallaQuiz { INICIO, QUIZ, RESULTADO }

@Composable
fun Practica10QuizInicio(modifier: Modifier = Modifier) {
    val preguntas = remember {
        listOf(
            Pregunta("¿Qué tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
            Pregunta("¿Cuál palabra declara una variable mutable?", listOf("val", "var", "class", "fun"), 1),
            Pregunta("¿Qué tipo guarda texto?", listOf("String", "Int", "Boolean", "Double"), 0),
            Pregunta("¿Qué estructura permite elegir entre casos?", listOf("when", "package", "import", "data"), 0),
            Pregunta("¿Qué anotación usa Compose?", listOf("@Composable", "@Override", "@Entity", "@Test"), 0)
        )
    }

    var pantalla by remember { mutableStateOf(PantallaQuiz.INICIO) }
    var nombre by remember { mutableStateOf("") }
    var indice by remember { mutableStateOf(0) }
    var seleccionada by remember { mutableStateOf<Int?>(null) }
    var respondida by remember { mutableStateOf(false) }
    var puntaje by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFF1F5F7)).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        when (pantalla) {
            PantallaQuiz.INICIO -> {
                CabeceraPractica("Reto de código", "Pon a prueba tus conocimientos", Color(0xFF6A61B5))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        indice = 0
                        seleccionada = null
                        respondida = false
                        puntaje = 0
                        pantalla = PantallaQuiz.QUIZ
                    },
                    enabled = nombre.length >= 2,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Comenzar") }
            }

            PantallaQuiz.QUIZ -> {
                val pregunta = preguntas[indice]

                CabeceraPractica("Reto de código", "Pon a prueba tus conocimientos", Color(0xFF6A61B5))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Pregunta ${indice + 1} de ${preguntas.size}")
                    Text("Puntaje: $puntaje")
                }

                LinearProgressIndicator(
                    progress = { (indice + 1) / preguntas.size.toFloat() },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    pregunta.texto,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                pregunta.opciones.forEachIndexed { i, opcion ->
                    val esCorrecta = respondida && i == pregunta.correcta
                    val esIncorrecta = respondida && seleccionada == i && i != pregunta.correcta

                    val fondo = when {
                        esCorrecta -> Color(0xFFE1F4E5)
                        esIncorrecta -> Color(0xFFFFE4E1)
                        else -> Color.White
                    }

                    val borde = when {
                        esCorrecta -> Color(0xFF2E7D32)
                        esIncorrecta -> Color(0xFFC62828)
                        else -> Color(0xFFD8D8D8)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(fondo, RoundedCornerShape(14.dp))
                            .border(1.dp, borde, RoundedCornerShape(14.dp))
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = seleccionada == i,
                            onClick = {
                                if (!respondida) seleccionada = i
                            },
                            enabled = !respondida
                        )
                        Text(opcion, modifier = Modifier.padding(top = 12.dp))
                    }
                }

                if (respondida && seleccionada != pregunta.correcta) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
                    ) {
                        Text(
                            "Incorrecto. La respuesta correcta es ${pregunta.opciones[pregunta.correcta]}.",
                            modifier = Modifier.padding(12.dp),
                            color = Color(0xFFB3261E)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            respondida = true
                            if (seleccionada == pregunta.correcta) puntaje++
                        },
                        enabled = seleccionada != null && !respondida,
                        modifier = Modifier.weight(1f)
                    ) { Text("Confirmar") }

                    Button(
                        onClick = {
                            if (indice == preguntas.lastIndex) {
                                pantalla = PantallaQuiz.RESULTADO
                            } else {
                                indice++
                                seleccionada = null
                                respondida = false
                            }
                        },
                        enabled = respondida,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (indice == preguntas.lastIndex) "Ver resultados" else "Siguiente")
                    }
                }
            }

            PantallaQuiz.RESULTADO -> {
                val mensaje = when (puntaje) {
                    5 -> "Perfecto"
                    3, 4 -> "Buen trabajo"
                    else -> "Sigue practicando"
                }

                CabeceraPractica("Reto de código", "Pon a prueba tus conocimientos", Color(0xFF6A61B5))
                Text("Resultado de $nombre", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("$puntaje de ${preguntas.size}", fontSize = 42.sp, fontWeight = FontWeight.Bold)
                Text(mensaje, fontSize = 20.sp)

                Button(
                    onClick = {
                        indice = 0
                        seleccionada = null
                        respondida = false
                        puntaje = 0
                        pantalla = PantallaQuiz.QUIZ
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Reintentar") }

                OutlinedButton(
                    onClick = {
                        nombre = ""
                        pantalla = PantallaQuiz.INICIO
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Cambiar jugador") }
            }
        }
    }
}