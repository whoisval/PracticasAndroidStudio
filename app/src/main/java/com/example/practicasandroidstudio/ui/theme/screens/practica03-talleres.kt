package com.example.practicasandroidstudio.ui.theme.screens

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.Components.CabeceraPractica


@Composable
fun Practica03TallerInicio(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var taller by remember { mutableStateOf("Fotografía") }
    var aviso by remember { mutableStateOf(false) }
    var intento by remember { mutableStateOf(false) }
    var inscrito by remember { mutableStateOf(false) }

    val correoValido = correo.indexOf('@') > 0 && correo.substringAfter("@").contains(".")
    val edadValida = (edad.toIntOrNull() ?: -1) in 12..99
    val nombreValido = nombre.trim().length >= 3
    val valido = nombreValido && correoValido && edadValida && aviso

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFF1F5f7)).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CabeceraPractica("Casa del Lago", "Tu próximo taller empieza aquí", Color.Black)
        Text("Selecciona una actividad y completa tus datos", color = Color(0xFF79594C))

        if (!inscrito) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                isError = intento && !nombreValido
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth(),
                isError = intento && !correoValido,
                supportingText = {
                    if (intento && !correoValido ) Text("Ingresa un correo válido")
                }
            )

            OutlinedTextField(
                value = edad,
                onValueChange = { if (it.all(Char::isDigit)) edad = it },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = intento && !edadValida
            )

            Text("Taller", fontWeight = FontWeight.SemiBold)
            listOf("Pintura", "Fotografía", "Guitarra").forEach {
                Row {
                    RadioButton(selected = taller == it, onClick = { taller = it })
                    Text(it, modifier = Modifier.padding(top = 12.dp))
                }
            }

            Row {
                Checkbox(checked = aviso, onCheckedChange = { aviso = it })
                Text("Acepto el aviso de privacidad", modifier = Modifier.padding(top = 12.dp))
            }

            Button(
                onClick = {
                    intento = true
                    if (valido) inscrito = true
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Inscribirme")
            }

            Text("Cupos sujetos a disponibilidad", color = Color.Gray, fontSize = 12.sp)
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Inscripción confirmada", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Nombre: $nombre")
                    Text("Correo: $correo")
                    Text("Edad: $edad")
                    Text("Taller: $taller")
                }
            }

            Button(
                onClick = {
                    nombre = ""
                    correo = ""
                    edad = ""
                    taller = "Pintura"
                    aviso = false
                    intento = false
                    inscrito = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nueva inscripción")
            }
        }
    }
}