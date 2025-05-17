package com.example.graphs.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object HomeRoute

    @Serializable
    object BarRoute

    @Serializable
    object PieRoute

    @Serializable
    object CandleRoute

}