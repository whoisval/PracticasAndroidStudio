package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme

@Composable
fun Practica01Inicio(modifier: Modifier = Modifier) {
    var x by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        Text(text = x.toString(), fontSize = 50.sp)

        Button(onClick = { x += 1 }) {
            Text("Sumar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    PracticasAndroidStudioTheme() {
        Practica01Inicio()
    }
}