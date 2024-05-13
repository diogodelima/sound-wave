package com.diogo.soundwave.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.diogo.soundwave.service.MusicService
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CompletableDeferred

data class PlayerTrack(

    var track: Track?,
    var playing: Boolean,
    var currentSecond: Float = 0f

) : AbstractYouTubePlayerListener(){

    val playerTrackData: MutableLiveData<PlayerTrack> = MutableLiveData()
    lateinit var youTubePlayer: YouTubePlayer
    private val playerInitialized = CompletableDeferred<Unit>()

    override fun onReady(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer = youTubePlayer
        Log.d("MainActivity", "INITIALIZED")
        playerInitialized.complete(Unit)
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        this.currentSecond = second
        updateData(this)
        Log.d("MainActivity", "UPDATE - SECOND")
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        this.playing = state != PlayerConstants.PlayerState.PAUSED
        updateData(this)
        Log.d("MainActivity", "UPDATE")
    }

    fun play(service: MusicService, track: Track){

        if (this.track == track && this.playing)
            return

        this.playing = true

        if (this.track == track){
            this.youTubePlayer.play()
            return
        }

        this.track = track
        this.currentSecond = 0f

        val trackName = track.name
        val artistName = track.getArtist().name
        this.youTubePlayer.loadVideo(service.searchVideo(trackName, artistName)().id, 0f)
    }

    suspend fun waitUntilInitialized() {
        playerInitialized.await()
    }

    fun isPlaying(track: Track?) : Boolean {
        return track != null && this.track?.id == track.id && this.playing
    }

    fun getCurrentSecond(track: Track?) : Float {

        if (this.track == track)
            return this.currentSecond

        return 0f
    }

    fun isInitializated() : Boolean {
        return ::youTubePlayer.isInitialized
    }

    fun updateData(playerTrack: PlayerTrack) {
        playerTrackData.value = playerTrack.copy()
    }

}