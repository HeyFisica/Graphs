package com.example.graphs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.graphs.ui.navigation.GraphNavigation
import com.example.graphs.ui.theme.GraphsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphsTheme {

                    Column(modifier = Modifier.fillMaxSize()) {
                        GraphNavigation()
                  }

                }
            }
        }
    }


