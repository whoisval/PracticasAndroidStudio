package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

@Composable
fun Practica07Inicio(
    modifier: Modifier = Modifier
) {

    val calculadora = rememberSaveable(
        saver = listSaver<Calculadora, String>(
            save = {
                it.guardar()
            },
            restore = { estado ->
                Calculadora().apply {
                    restaurar(estado)
                }
            }
        )
    ) {
        Calculadora()
    }

    var pantalla by rememberSaveable {
        mutableStateOf(calculadora.texto)
    }

    val filas = listOf(
        listOf("AC", "<", "%", "÷"),
        listOf("7", "8", "9", "x"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("±", "0", ".", "=")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF13161B))
            .padding(12.dp)
    ) {

        Text(
            text = "Calculadora",
            color = Color(0xFFA8C7FA),
            fontSize = 22.sp,
            modifier = Modifier.padding(12.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {

            Text(
                text = pantalla,
                color = Color(0xFFE3E3E3),
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                maxLines = 1,
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .semantics {
                        liveRegion = LiveRegionMode.Polite
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            filas.forEach { fila ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    fila.forEach { tecla ->

                        val fondo = when (tecla) {

                            "=" -> Color(0xFFA8C7FA)

                            "+", "-", "x", "÷" ->
                                Color(0xFF344763)

                            "AC", "<", "%" ->
                                Color(0xFF3D454F)

                            else ->
                                Color(0xFF242930)
                        }

                        val descripcion = when (tecla) {

                            "AC" -> "Borrar todo"
                            "<" -> "Borrar último dígito"
                            "±" -> "Cambiar signo"
                            "÷" -> "Dividir"
                            "x" -> "Multiplicar"
                            "-" -> "Restar"
                            "+" -> "Sumar"
                            "=" -> "Igual"
                            "%" -> "Porcentaje"
                            "." -> "Punto decimal"

                            else -> tecla
                        }

                        Button(
                            onClick = {

                                calculadora.pulsar(tecla)

                                pantalla = calculadora.texto
                            },

                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .semantics {
                                    contentDescription = descripcion
                                },

                            shape = RoundedCornerShape(32.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = fondo,

                                contentColor =
                                    if (tecla == "=") {
                                        Color(0xFF102544)
                                    } else {
                                        Color(0xFFE3E3E3)
                                    }
                            ),

                            contentPadding = PaddingValues(0.dp)
                        ) {

                            Text(
                                text = tecla,
                                fontSize = 26.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 760
)
@Composable
fun Practica07Preview() {

    PracticasAndroidStudioTheme {

        Practica07Inicio()
    }
}