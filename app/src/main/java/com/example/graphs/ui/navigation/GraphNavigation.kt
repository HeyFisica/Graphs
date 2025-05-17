package com.example.graphs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.graphs.ui.screen.BarChartScreen
import com.example.graphs.ui.screen.CandleChartScreen
import com.example.graphs.ui.screen.HomeScreen
import com.example.graphs.ui.screen.PieChartScreen

@Composable
fun GraphNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeRoute) {

        composable<Routes.HomeRoute> {
            HomeScreen(navController)
        }

        composable<Routes.BarRoute> {
            BarChartScreen(navController)
        }

        composable<Routes.PieRoute> {
            PieChartScreen(navController)
        }

        composable<Routes.CandleRoute> {
            CandleChartScreen(navController)
        }
    }





}