package com.example.graphs.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarChartScreen(navController: NavController) {
    val darkGreen = Color(0xFF1A4314)

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text("Bar Chart")},
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor=darkGreen)
                )
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val stepSize = 5
            val barsData = DataUtils.getBarChartData(
                listSize = 8,
                maxRange = 8,
                barChartType = BarChartType.VERTICAL,
                dataCategoryOptions = DataCategoryOptions()
            )
            val xAxisData = AxisData.Builder()
                .axisStepSize(100.dp)
                .steps(barsData.size - 1)
                .bottomPadding(40.dp)
                .axisLabelAngle(20f)
                .labelData { index -> barsData[index].label }
                .axisLineColor(MaterialTheme.colorScheme.tertiary)
                .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(stepSize)
                .labelAndAxisLinePadding(20.dp)
                .axisOffset(20.dp)
                .labelData { index -> (index * (200 / stepSize)).toString() }
                .axisLineColor(MaterialTheme.colorScheme.tertiary)
                .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                .build()

            val barChartData = BarChartData(
                chartData = barsData,
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                backgroundColor = MaterialTheme.colorScheme.surface


            )

            BarChart(
                modifier = Modifier
                    .height(350.dp),
                barChartData = barChartData
            )

            val random = remember { mutableStateOf(0) }
             Text("Click Me I am a random number ${random.value}")



            Button(onClick = {random.value = Random.nextInt(10, 20) }) {
                Text("Refresh")

            }
        }
    }
}