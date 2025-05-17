package com.example.graphs.ui.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieChart(navController: NavController, onTrigger: () -> Unit) {
    val darkGreen = Color(0xFF1A4314)

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Pie Chart") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = darkGreen)
        )
    })
    { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val pieChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice("SciFi", 65f, Color(0xFF333333)),
                    PieChartData.Slice("Comedy", 35f, Color(0xFF666a86)),
                    PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
                    PieChartData.Slice("Romance", 40f, Color(0xFFF53844))
                ),
                plotType = PlotType.Pie
            )

            val pieChartConfig = PieChartConfig(
                activeSliceAlpha = 0.5f,
                isAnimationEnable = true,
                showSliceLabels = false,
                animationDuration = 1500
            )
            PieChart(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp),
                pieChartData,
                pieChartConfig
            )

            val random = remember { mutableStateOf(0) }
            Text(
                "Click Me I am a random number: ${random.value}", modifier = Modifier,
            )
            Text(
                " ${random.value}", modifier = Modifier
                    .clickable {
                        onTrigger()
                    }
                    .padding(8.dp)


                    .align(Alignment.CenterHorizontally),


                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )



            Button(onClick = { random.value = Random.nextInt(1, 100) }) {
                Text("Refresh")

            }

        }
    }

}

@Composable
fun PieChartScreen(navController: NavController) {
    var recomposeTrigger by remember { mutableStateOf(0) }

    key(recomposeTrigger) {
        PieChart(navController = navController,
            onTrigger = {
                recomposeTrigger = Random.nextInt()
            }
        )
    }
}