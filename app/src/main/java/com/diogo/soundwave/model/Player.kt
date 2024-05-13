package com.diogo.soundwave.model

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.diogo.soundwave.service.MusicService
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Player (

    val service: MusicService,
    val playerTrack: PlayerTrack = PlayerTrack(null, false)

){

    fun play(track: Track){
        this.playerTrack.play(service, track)
    }

}