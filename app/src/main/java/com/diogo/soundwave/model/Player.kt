package com.diogo.soundwave.model

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.diogo.soundwave.service.MusicService
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Player (

    val service: MusicService,
    var track: Track?,
    private var currentSecond: Float

) : AbstractYouTubePlayerListener() {

    constructor(service: MusicService) : this(service,null, 0f)

    private lateinit var youTubePlayer: YouTubePlayer

    override fun onReady(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer = youTubePlayer
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        this.currentSecond = second
    }

    fun play(){

        while (!::youTubePlayer.isInitialized){}

        val trackName = this.track?.name!!
        val artistName = this.track?.artist!!().name
        this.youTubePlayer.loadVideo(service.searchVideo(trackName, artistName)().id, this.currentSecond)
    }

    fun getCurrentSecond(track: Track) : Float {

        if (track.name == this.track?.name && track.artist().name == this.track?.artist!!().name)
            return this.currentSecond

        return 0f
    }

}