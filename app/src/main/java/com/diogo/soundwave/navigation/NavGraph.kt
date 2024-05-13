package com.diogo.soundwave.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.diogo.soundwave.model.Player
import com.diogo.soundwave.model.Track
import com.diogo.soundwave.screen.HomeScreen
import com.diogo.soundwave.screen.trackScreen
import com.diogo.soundwave.service.MusicService
import kotlinx.coroutines.*

@Composable
fun setupNavGraph(
    navController: NavHostController,
    service: MusicService,
    player: Player
){

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController)
        }

        composable(
            route = Screen.Track.route,
            arguments = listOf(
                navArgument(TRACK_ID){
                    type = NavType.StringType
                }
            )
        ){
            val trackId = it.arguments?.getString(TRACK_ID)!!
            val trackRemember = remember { mutableStateOf<Track?>(null) }

            LaunchedEffect(Unit) {
                trackRemember.value = withContext(Dispatchers.IO) {
                    service.searchTrackById(trackId, Int.MAX_VALUE).firstOrNull()
                }
            }

            val track = trackRemember.value

            if (track != null)
                trackScreen(track, player)

        }

    }

}