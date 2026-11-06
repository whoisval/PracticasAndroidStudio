package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme
import com.example.practicasandroidstudio.ui.theme.components.micard

@Composable
fun Practica04Inicio(modifier: Modifier = Modifier) {
    val listaNombres = listOf(
        "Said", "Alejandro", "Said", "Alejandro", "Said", "Alejandro", "Said", "Alejandro"
    )

    Column(modifier = modifier){
        listaNombres.forEach { nombre ->
            micard(modifier, nombre)
        }
    }

    LazyColumn(modifier = modifier) {
        items(listaNombres) { nombre ->
            micard(modifier = Modifier, nombre = nombre)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    PracticasAndroidStudioTheme() {
        Practica04Inicio()
    }
}