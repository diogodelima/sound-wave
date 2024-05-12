package com.diogo.soundwave.model

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.diogo.soundwave.service.MusicService
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Player (

    val service: MusicService,
    var track: Track?,
    var state: PlayerConstants.PlayerState = PlayerConstants.PlayerState.UNKNOWN,
    private var currentSecond: Float

) : AbstractYouTubePlayerListener() {

    constructor(service: MusicService) : this(service = service, track = null, currentSecond = 0f)

    lateinit var youTubePlayer: YouTubePlayer

    override fun onReady(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer = youTubePlayer
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        this.currentSecond = second
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        this.state = state
    }

    fun play(track: Track){

        while (!::youTubePlayer.isInitialized){}

        if (this.track?.id == track.id && state != PlayerConstants.PlayerState.PLAYING){
            this.youTubePlayer.play()
            return
        }

        this.track = track

        val trackName = track.name
        val artistName = track.getArtist().name
        this.youTubePlayer.loadVideo(service.searchVideo(trackName, artistName)().id, this.currentSecond)
    }

    fun getCurrentSecond(track: Track) : Float {

        if (track.id == this.track?.id)
            return this.currentSecond

        return 0f
    }

    fun isPlaying(track: Track) : Boolean {
        return this.track?.id == track.id && this.state == PlayerConstants.PlayerState.PLAYING
    }

    fun isInitializated() : Boolean {
        return ::youTubePlayer.isInitialized
    }

}