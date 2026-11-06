package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Practica07ClinicaInicio(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var recordarme by remember { mutableStateOf(false) }
    var mostrarPassword by remember { mutableStateOf(false) }
    var intentos by remember { mutableStateOf(0) }
    var exito by remember { mutableStateOf(false) }

    val bloqueado = intentos >= 3

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFF1F5F7)).padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(88.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("+", fontSize = 40.sp, color = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(Modifier.height(12.dp))
        Text("Portal de pacientes", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color(0xFF165B61))
        Text("Accede a tus citas y recordatorios", color = Color.Gray)

        Spacer(Modifier.height(20.dp))

        if (!exito) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { mostrarPassword = !mostrarPassword }) {
                        Text(if (mostrarPassword) "Ocultar" else "Ver")
                    }
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = recordarme, onCheckedChange = { recordarme = it })
                Text("Recordarme")
            }

            if (intentos > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE5E2))
                ) {
                    Text(
                        if (bloqueado) "Acceso bloqueado. Intenta más tarde."
                        else "Correo o contraseña incorrectos (intento $intentos de 3)",
                        modifier = Modifier.padding(12.dp),
                        color = Color(0xFFB3261E)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (email == "paciente@vida.mx" && password == "Vida2025") {
                        exito = true
                    } else {
                        intentos++
                    }
                },
                enabled = !bloqueado,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) { Text("Entrar") }

            Text("Acceso de demostración · Sin servidor", color = Color.Gray, fontSize = 12.sp)
        } else {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Bienvenido", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(email)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            exito = false
                            email = ""
                            password = ""
                            intentos = 0
                            recordarme = false
                        }
                    ) { Text("Cerrar sesión") }
                }
            }
        }
    }
}

