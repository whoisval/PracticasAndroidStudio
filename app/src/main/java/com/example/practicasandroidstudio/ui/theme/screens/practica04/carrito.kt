
package com.example.practicasandroidstudio.ui.theme.screens.practica04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicasandroidstudio.ui.theme.Components.CabeceraPractica
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme
import java.util.Locale

data class Producto(
    val nombre: String,
    val precio: Double
)

@Composable
fun Practica04CarritoInicio(modifier: Modifier = Modifier) {

    val catalago = listOf(
        Producto("Tortillas 1Kg", 24.0),
        Producto("Leche 1L", 27.5),
        Producto("Huevo 12Pz", 52.0),
        Producto("Frijol 1Kg", 38.0)
    )

    val cantidades = remember {
        mutableStateListOf(0, 0, 0, 0)
    }

    var pedidoConfirmado by remember {
        mutableStateOf(false)
    }

    val articulos = cantidades.sum()

    val subtotal = catalago.indices.sumOf {
        catalago[it].precio * cantidades[it]
    }

    val descuento = if (subtotal >= 200) {
        subtotal * .10
    } else {
        0.0
    }

    val despuesDescuento = subtotal - descuento

    val envio = if (articulos == 0) {
        0.0
    } else if (despuesDescuento >= 150) {
        0.0
    } else {
        25.0
    }

    val total = despuesDescuento + envio

    val verde = Color(0xFF287B78)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F7))
            .verticalScroll(rememberScrollState())
            .padding(18.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        CabeceraPractica(
            titulo = "Tienda Don Pepe",
            subtitulo = "Arma tu pedido fácilmente",
            color = verde
        )

        Text(
            text = "Nuestros productos",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        catalago.forEachIndexed { index, producto ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = producto.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$" + String.format(
                            Locale.US,
                            "%.2f",
                            producto.precio
                        ) + " c/u",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        OutlinedButton(
                            onClick = {
                                cantidades[index]--
                            },
                            enabled = cantidades[index] > 0
                        ) {
                            Text("-")
                        }

                        Text(
                            text = cantidades[index].toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            onClick = {
                                cantidades[index]++
                            },
                            enabled = cantidades[index] < 10,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = verde
                            )
                        ) {
                            Text("+")
                        }
                    }

                    Text(
                        text = "Importe: $" + String.format(
                            Locale.US,
                            "%.2f",
                            producto.precio * cantidades[index]
                        ),
                        color = verde,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Text(
            text = "Resumen del pedido",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Artículos")

                    Text(
                        text = articulos.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal")

                    Text(
                        text = "$" + String.format(
                            Locale.US,
                            "%.2f",
                            subtotal
                        ),
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Descuento 10%")

                    Text(
                        text = "-$" + String.format(
                            Locale.US,
                            "%.2f",
                            descuento
                        ),
                        color = verde,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Envío")

                    Text(
                        text = if (articulos > 0 && envio == 0.0) {
                            "GRATIS"
                        } else {
                            "$" + String.format(
                                Locale.US,
                                "%.2f",
                                envio
                            )
                        },
                        fontWeight = FontWeight.Bold
                    )
                }

                HorizontalDivider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "TOTAL",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$" + String.format(
                            Locale.US,
                            "%.2f",
                            total
                        ),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            OutlinedButton(
                onClick = {
                    cantidades.indices.forEach {
                        cantidades[it] = 0
                    }
                },
                enabled = articulos > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Vaciar")
            }

            Button(
                onClick = {
                    pedidoConfirmado = true
                },
                enabled = articulos > 0,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verde
                )
            ) {
                Text("Confirmar")
            }
        }
    }

    if (pedidoConfirmado) {

        AlertDialog(
            onDismissRequest = {
                pedidoConfirmado = false
            },

            title = {
                Text("Confirmar pedido")
            },

            text = {
                Text(
                    "Tu pedido contiene $articulos artículos " +
                            "con un total de $" +
                            String.format(Locale.US, "%.2f", total)
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        cantidades.indices.forEach {
                            cantidades[it] = 0
                        }

                        pedidoConfirmado = false
                    }
                ) {
                    Text("Aceptar")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        pedidoConfirmado = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Practica04CarritoPreview() {
    PracticasAndroidStudioTheme {
        Practica04CarritoInicio()
    }
}
