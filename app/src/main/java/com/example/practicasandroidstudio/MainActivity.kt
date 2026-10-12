package com.example.practicasandroidstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicasandroidstudio.ui.theme.PracticasAndroidStudioTheme
import com.example.practicasandroidstudio.ui.theme.screens.Practica01Inicio

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticasAndroidStudioTheme {
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
    PracticasAndroidStudioTheme {
        Practica01Inicio()
    }
}