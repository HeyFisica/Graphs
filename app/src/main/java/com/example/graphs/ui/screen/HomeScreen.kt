package com.example.graphs.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.graphs.R
import com.example.graphs.ui.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val darkGreen = Color(0xFF1A4314)



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Chart World", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = darkGreen
                ),


                )

        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Home") },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.HomeRoute)

                    }
                )
                DropdownMenuItem(
                    text = { Text("Bar Graph") },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.BarRoute)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Pie Graph") },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.PieRoute)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Candle Chart") },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.CandleRoute)
                    }
                )
            }
        }
        GetStartedUI(navController)

    }
}

@Composable
fun GetStartedUI(navController: NavController) {
    val darkGreen = Color(0xFF1A4314)


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//
//            .background(Color(0xff0f9d58))
//    ) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff0f9d58)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(R.drawable.bar_graph), contentDescription = "icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .padding(start = 22.dp, bottom = 22.dp)
                .align(Alignment.Start)


        )


        Text(
            text = "Track Your \n\n\nSpending \n\n\nEffortlessly",
            modifier = Modifier
                .padding(start = 22.dp)
                .align(Alignment.Start),


            color = darkGreen,
            fontSize = 55.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Manage your finances easily using \n" +
                    "intuitive and user friendly interface set\n" +
                    "financial goals and monitor your progress",
            modifier = Modifier
                .padding(start = 22.dp)
                .align(Alignment.Start),

            color = darkGreen,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { navController.navigate(Routes.BarRoute) },
            modifier = Modifier
                .fillMaxWidth()


                .padding(start = 22.dp, end = 22.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),

            colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
        ) {
            Text(
                "Get Started",
                modifier = Modifier
                    .align(Alignment.CenterVertically)

                    .padding(16.dp), Color.White, fontSize = 20.sp
            )
        }


    }
}



