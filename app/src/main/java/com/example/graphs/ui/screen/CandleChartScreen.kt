package com.example.graphs.ui.screen


import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandleChart(
    navController: NavController, onTrigger: () -> Unit,
    candleEntries: List<CandleEntry>,
) {
    val darkGreen = 0xFF1A4314

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Candle Chart")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(darkGreen)
                )
            )


        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                factory = { context ->
                    CandleStickChart(context).apply {
                        val dataSet = CandleDataSet(candleEntries, "Candles").apply {
                            color = Color.rgb(80, 80, 80)
                            shadowColor = Color.DKGRAY
                            shadowWidth = 0.7f
                            decreasingColor = Color.RED
                            decreasingPaintStyle = Paint.Style.FILL
                            increasingColor = Color.GREEN
                            increasingPaintStyle = Paint.Style.FILL
                            neutralColor = Color.BLUE
                            setDrawValues(false)
                        }

                        data = CandleData(dataSet)

                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        xAxis.setDrawGridLines(false)
                        axisLeft.setDrawGridLines(false)
                        axisRight.isEnabled = false
                        legend.isEnabled = false
                        description.isEnabled = false

                        isDragEnabled = true
                        setScaleEnabled(true)
                        setPinchZoom(true)

                        // Animate the chart!
                        animateX(1000)

                        invalidate()
                    }
                },
                update = { chart ->
                    val dataSet = CandleDataSet(candleEntries, "Candles").apply {
                        color = Color.rgb(80, 80, 80)
                        shadowColor = Color.DKGRAY
                        shadowWidth = 0.7f
                        decreasingColor = Color.RED
                        decreasingPaintStyle = Paint.Style.FILL
                        increasingColor = Color.GREEN
                        increasingPaintStyle = Paint.Style.FILL
                        neutralColor = Color.BLUE
                        setDrawValues(false)
                    }

                    chart.data = CandleData(dataSet)
                    chart.animateX(1000) // animate on data update
                    chart.invalidate()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

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

fun generateCandleEntries(): List<CandleEntry> {
    val entries = mutableListOf<CandleEntry>()
    for (i in 0 until 10) {
        val high = Random.nextFloat() * 100 + 200
        val low = high - Random.nextFloat() * 40
        val open = low + Random.nextFloat() * (high - low)
        val close = low + Random.nextFloat() * (high - low)
        entries.add(CandleEntry(i.toFloat(), high, low, open, close))
    }
    return entries
}


@Composable
fun CandleChartScreen(navController: NavController) {
    var recomposeTrigger by remember { mutableStateOf(0) }
    val entries by remember { mutableStateOf(generateCandleEntries()) }

    key(recomposeTrigger) {
        CandleChart(
            navController = navController,
            onTrigger = {
                recomposeTrigger = Random.nextInt()
            },
            candleEntries = entries
        )
    }
}