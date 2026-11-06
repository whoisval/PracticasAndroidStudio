package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

@Composable
fun Practica03Inicio(modifier: Modifier = Modifier) {
    var numero by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(0) }

    Column(modifier = Modifier .fillMaxSize() .padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Practica 03")

        OutlinedTextField(value = numero, onValueChange = { numero = it }, label = { Text("Ingresa un número") },
            keyboardOptions = KeyboardOptions
        (keyboardType = KeyboardType.Number))

        Button( onClick = {
            cantidad = numero.toIntOrNull() ?: 0
        }) {
            Text("Imprimir")
        }

        repeat(cantidad) {
            ImprimirCaracter("*")
        }
    }
}

@Composable
fun ImprimirCaracter(caracter: String) {
    Text(text = caracter)
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    PracticasAndroidStudioTheme() {
        Practica03Inicio()
    }
}