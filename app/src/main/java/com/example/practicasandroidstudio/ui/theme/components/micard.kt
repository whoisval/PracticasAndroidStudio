package com.example.practicasandroidstudio.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
package com.example.practicadelegate.ui.theme.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun micard(
    modifier: Modifier = Modifier,
    nombre: String,
    eliminar: () -> Unit,
    actualizar: () -> Unit
) {
fun micard(modifier: Modifier = Modifier, nombre: String) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(
                width = 2.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = nombre,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Actualizar",
                modifier = Modifier
                    .clickable {
                        actualizar()
                    }
                    .padding(10.dp)
            )

            Text(
                text = "Eliminar",
                modifier = Modifier
                    .clickable {
                        eliminar()
                    }
                    .padding(10.dp)
            )
        }
    }

    Spacer(modifier = Modifier.padding(5.dp))
}
                shape = RoundedCornerShape(2.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = nombre)
    }
    Spacer(modifier = Modifier.padding(5.dp))
}
