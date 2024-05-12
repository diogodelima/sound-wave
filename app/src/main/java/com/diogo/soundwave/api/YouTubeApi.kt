package com.diogo.soundwave.api

import com.diogo.soundwave.dto.SearchVideoDto
import com.diogo.soundwave.dto.VideoDto
import com.diogo.soundwave.model.Player
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
                val url = this.javaClass.classLoader?.getResourceAsStream("youtube_web_api_key.txt")
                BufferedReader(InputStreamReader(url)).use { return it.readLine() }
            }

        private const val YOUTUBE = "https://www.googleapis.com/youtube/v3/search?part=snippet"

        private val SEARCH_VIDEO = "$YOUTUBE&q=%s&type=video&maxResults=%d&key=$YOUTUBE_API_KEY"

    }

    fun searchVideo(trackName: String, artistName: String, maxResults: Int) : List<VideoDto> {

        val url = URL(String.format(SEARCH_VIDEO, "$trackName+$artistName", maxResults))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val searchVideo = gson.fromJson(it, SearchVideoDto::class.java)
                return searchVideo.items
            }

        }

    }

    fun init(youTubePlayerView: YouTubePlayerView, player: Player){
        youTubePlayerView.addYouTubePlayerListener(player)
    }

}