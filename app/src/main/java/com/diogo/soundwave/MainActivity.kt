package com.diogo.soundwave

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diogo.soundwave.model.Player
import com.diogo.soundwave.navigation.setupNavGraph
import com.diogo.soundwave.service.MusicService
import com.diogo.soundwave.ui.theme.SoundWaveTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val service = MusicService()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundWaveTheme {

                val youTubePlayerView = YouTubePlayerView(this)
                val player = Player(service)

                service.initYouTube(youTubePlayerView, player)

                navController = rememberNavController()
                setupNavGraph(navController, service, player)

                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        player.track = service.searchTrackByName("Farda", 1).first()
                        player.play()
                    }
                }

            }
        }
    }
}