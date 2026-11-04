
package com.example.practicasandroidstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme
import com.example.practicasandroidstudio.ui.theme.screens.Practica07Inicio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme
import com.example.practicasandroidstudio.ui.theme.screens.Practica02LecturaInicio
import com.example.practicasandroidstudio.ui.theme.screens.practica01.Practica01AforoInicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica08Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica06Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica05Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica04Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica03Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica02Inicio
import com.example.practicasandroidstudio.ui.theme.screens.Practica01Inicio

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            PracticasAndroidStudioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Practica01AforoInicio(
                    Practica08Inicio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Practica02LecturaInicio(
                    Practica07Inicio(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainPreview() {
    PracticasAndroidStudioTheme {
        Practica02LecturaInicio()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Practica06Inicio(modifier = Modifier.padding(innerPadding),)
                    Practica05Inicio(modifier = Modifier.padding(innerPadding))
                    Practica04Inicio(modifier = Modifier.padding(innerPadding))
                    Practica03Inicio(modifier = Modifier.padding(innerPadding))
                    Practica02Inicio(modifier = Modifier.padding(innerPadding))
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Practica01Inicio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PracticasAndroidStudioTheme  {
        Practica01AforoInicio()
    PracticasAndroidStudioTheme {
        Practica08Inicio()
        Practica06Inicio()
        Practica05Inicio()
        Practica04Inicio()
        Practica03Inicio()
        Practica02Inicio()
        Practica01Inicio()
    }
}
