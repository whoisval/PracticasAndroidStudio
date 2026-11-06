
package com.example.practicasandroidstudio.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val Morado = Color(0xFF6E57AA)
private val Fondo = Color(0xFFF1F5F7)

enum class Interlineado(val factor: Float) {
    COMPACTO(1f),
    NORMAL(1.3f),
    AMPLIO(1.6f)
}

@Composable
fun Practica02LecturaInicio(
    modifier: Modifier = Modifier
) {
    var tam by remember { mutableStateOf(18f) }
    var interlineado by remember {
        mutableStateOf(Interlineado.NORMAL)
    }
    var modoOscuro by remember { mutableStateOf(false) }
    var serif by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fondo)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // ENCABEZADO
        CabeceraPractica(
            titulo = "Lectura a tu medida",
            subtitulo = "Personaliza cada página",
            color = Morado,
            icono = "Aa"
        )

        // TAMAÑO DE LETRA
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Tamaño de letra",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("A", fontSize = 14.sp)

                    Slider(
                        value = tam,
                        onValueChange = { tam = it },
                        valueRange = 14f..32f,
                        modifier = Modifier.weight(1f)
                    )

                    Text("A", fontSize = 26.sp)
                }

                Text(
                    text = "${tam.toInt()} sp",
                    color = Morado,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // INTERLINEADO
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    "Interlineado",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Interlineado.entries.forEach { opcion ->

                        FilterChip(
                            selected = interlineado == opcion,
                            onClick = {
                                interlineado = opcion
                            },
                            label = {
                                Text(
                                    opcion.name.lowercase()
                                        .replaceFirstChar {
                                            it.uppercase()
                                        }
                                )
                            }
                        )
                    }
                }
            }
        }

        // APARIENCIA
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    "Apariencia",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Modo oscuro",
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = modoOscuro,
                        onCheckedChange = {
                            modoOscuro = it
                        }
                    )
                }

                HorizontalDivider()

                Spacer(Modifier.height(12.dp))

                Text(
                    "Tipo de fuente",
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    FilterChip(
                        selected = serif,
                        onClick = { serif = true },
                        label = { Text("Serif") }
                    )

                    FilterChip(
                        selected = !serif,
                        onClick = { serif = false },
                        label = { Text("Sans") }
                    )
                }
            }
        }

        // VISTA PREVIA
        Text(
            text = "ASÍ SE VERÁ TU LECTURA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Morado
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (modoOscuro)
                    Color(0xFF1F1F23)
                else
                    Color.White
            )
        ) {

            Text(
                text = "Era una noche tranquila en el pueblo " +
                        "cuando las campanas comenzaron a sonar, " +
                        "y nadie supo por qué.",

                modifier = Modifier.padding(20.dp),

                color = if (modoOscuro)
                    Color.White
                else
                    Color.Black,

                fontSize = tam.sp,

                lineHeight = (tam * interlineado.factor).sp,

                fontFamily = if (serif)
                    FontFamily.Serif
                else
                    FontFamily.SansSerif
            )
        }

        // RESTABLECER
        OutlinedButton(
            onClick = {
                tam = 18f
                interlineado = Interlineado.NORMAL
                modoOscuro = false
                serif = false
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Restablecer ajustes")
        }
    }
}

// COMPONENTE DE ENCABEZADO
@Composable
fun CabeceraPractica(
    titulo: String,
    subtitulo: String,
    color: Color,
    icono: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),

            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = subtitulo,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }

            Text(
                text = icono,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Practica02LecturaPreview() {
    Practica02LecturaInicio()
}
