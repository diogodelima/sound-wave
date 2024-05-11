package com.diogo.soundwave

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diogo.soundwave.navigation.setupNavGraph
import com.diogo.soundwave.service.MusicService
import com.diogo.soundwave.ui.theme.SoundWaveTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val service = MusicService()
    private lateinit var navController: NavHostController

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundWaveTheme {

                navController = rememberNavController()
                setupNavGraph(navController, service)

                val view = YouTubePlayerView(this)
                /*GlobalScope.launch {
                    //val track = service.searchTrack("Gson", "Farda", Int.MAX_VALUE).first()
                    val video = service.searchVideo("Farda", "Gson")()
                    service.playVideo(view, video)
                }*/

            }
        }
    }
}