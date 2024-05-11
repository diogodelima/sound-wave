package com.diogo.soundwave.api

import com.diogo.soundwave.dto.VideoDto
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class YouTubeApi(

    private val gson: Gson = Gson()

) {

    companion object {

        private val YOUTUBE_API_KEY: String
            get() {
                return "AIzaSyA_jvyZOCH4BUsXfwcTkA2TuGEUszbkqRQ"
            }

        private const val YOUTUBE = "https://www.googleapis.com/youtube/v3/search?part=snippet"

        private val SEARCH_VIDEO = "$YOUTUBE&q=%s&type=video&maxResults=%d&key=$YOUTUBE_API_KEY"

    }

    /*fun searchVideo(trackName: String, artistName: String, maxResults: Int) : List<VideoDto> {

        val url = URL(String.format(SEARCH_VIDEO, "$trackName+$artistName", maxResults))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val searchVideo = gson.fromJson(it, SearchVideoDto::class.java)
                return searchVideo.items
            }

        }

    }*/

    fun playVideo(youTubePlayerView: YouTubePlayerView, videoId: String){

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.setVolume(100)
                youTubePlayer.loadVideo(videoId, 0f)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

            }

        })

    }

}