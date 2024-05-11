package com.diogo.soundwave.navigation

const val ARTIST_NAME = "artist_name"
const val TRACK_NAME = "track_name"

sealed class Screen(

    val route: String

) {

    data object Home: Screen("home")
    data object Track: Screen("track/{$ARTIST_NAME}/{$TRACK_NAME}"){

        fun route(artistName: String, trackName: String) : String {
            return this.route
                .replace("{$ARTIST_NAME}", artistName)
                .replace("{$TRACK_NAME}", trackName)
        }

    }

}