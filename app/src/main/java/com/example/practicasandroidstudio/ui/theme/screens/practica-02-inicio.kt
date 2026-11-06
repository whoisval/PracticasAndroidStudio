package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

@Composable
fun Practica02Inicio(modifier: Modifier = Modifier) {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf(0) }

    Column(modifier = Modifier .fillMaxSize() .padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Suma de dos números")
        OutlinedTextField(value = numero1, onValueChange = { numero1 = it}, label = { Text("Número 1") })

        OutlinedTextField(value = numero2, onValueChange = { numero2 = it}, label = { Text("Número 2") })

        Button(onClick = {
            val num1 = numero1.toIntOrNull() ?: 0
            val num2 = numero2.toIntOrNull() ?: 0
            resultado = num1 + num2 } ) {
            Text("Sumar")
        }

        Text(
            text = "Resultado: $resultado"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    PracticasAndroidStudioTheme() {
        Practica02Inicio()
    }
}