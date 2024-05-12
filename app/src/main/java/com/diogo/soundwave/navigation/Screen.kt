package com.diogo.soundwave.navigation

const val TRACK_NAME = "track_name"

sealed class Screen(

    val route: String

) {

    data object Home: Screen("home")
    data object Track: Screen("track/{$TRACK_NAME}"){

        fun route(trackName: String) : String {
            return this.route
                .replace("{$TRACK_NAME}", trackName)
        }

    }

}