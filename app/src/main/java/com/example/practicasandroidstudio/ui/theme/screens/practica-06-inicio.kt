package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

@Composable
fun Practica06Inicio(
    modifier: Modifier = Modifier
) {

    var textoColor by remember {
        mutableStateOf("")
    }

    val listaColores = remember {
        mutableStateListOf<Color>()
    }

    var error by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Lista de colores")

        OutlinedTextField(
            value = textoColor,
            onValueChange = {
                textoColor = it
            },
            label = {
                Text("Ingresa un color")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val color = validarColor(textoColor)

                if (color != null) {
                    listaColores.add(color)
                    textoColor = ""
                    error = ""
                } else {
                    error = "Color no válido"
                }
            }
        ) {
            Text("Agregar")
        }

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listaColores) { color ->
                CuadroColor(color)
            }
        }
    }
}

@Composable
fun CuadroColor(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color)
    )
}

fun validarColor(nombre: String): Color? {
    return when (nombre.trim().lowercase()) {
        "rojo" -> Color.Red
        "azul" -> Color.Blue
        "verde" -> Color.Green
        "amarillo" -> Color.Yellow
        "negro" -> Color.Black
        "blanco" -> Color.White
        "gris" -> Color.Gray
        "cyan" -> Color.Cyan
        "magenta" -> Color.Magenta
        else -> null
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    PracticasAndroidStudioTheme {
        Practica06Inicio()
    }
}