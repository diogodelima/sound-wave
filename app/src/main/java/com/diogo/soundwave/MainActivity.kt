package com.diogo.soundwave

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diogo.soundwave.model.Player
import com.diogo.soundwave.navigation.setupNavGraph
import com.diogo.soundwave.service.MusicService
import com.diogo.soundwave.ui.theme.SoundWaveTheme
import com.diogo.soundwave.ui.theme.background
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val service = MusicService()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundWaveTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(background)
                ) {

                    val youTubePlayerView = YouTubePlayerView(this)
                    val player = Player(service)

                    service.initYouTube(youTubePlayerView, player)

                    LaunchedEffect(Unit) {
                        withContext(Dispatchers.IO) {

                            while (!player.isInitializated()){}

                            val track = service.searchTrackByName("3,14", 1).first()
                            player.play(track)
                        }
                    }

                    navController = rememberNavController()
                    setupNavGraph(navController, service, player)

                }

            }
        }
    }
}