package com.diogo.soundwave

import com.diogo.soundwave.service.MusicService
import org.junit.Test

class MusicServiceTest{

    private val service: MusicService = MusicService()

    @Test
    fun searchArtist(){
        val artist = service.searchArtist("Gson", 1).first()
        println(artist.toString())
    }

    @Test
    fun searchTrack(){
        val track = service.searchTrack("Farda", 1).first()
        println(track.toString())
        println(track.artist().toString())
    }

}