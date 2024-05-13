package com.diogo.soundwave.navigation

const val TRACK_ID = "track_id"

sealed class Screen(

    val route: String

) {

    data object Home: Screen("home")
    data object Track: Screen("track/{$TRACK_ID}"){

        fun route(trackId: String) : String {
            return this.route
                .replace("{$TRACK_ID}", trackId)
        }

    }

}