package com.diogo.soundwave.api

import com.diogo.soundwave.dto.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DeezerApi(

    private val gson: Gson = Gson()

) {

    companion object {

        private const val DEEZER = "https://api.deezer.com/"

        private const val GET_ARTIST = "${DEEZER}artist/%s"

        private const val SEARCH_ARTIST = "${DEEZER}search/artist?q=%s&index=%d"

        private const val SEARCH_TRACK_BY_NAME = "${DEEZER}search/track?q=%s&index=%d"

        private const val SEARCH_TRACK_BY_ID = "${DEEZER}track/%s"

    }

    fun searchArtist(artistId: String) : ArtistDto {

        val url = URL(String.format(GET_ARTIST, artistId))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val artist = gson.fromJson(it, ArtistDto::class.java)
                return artist
            }

        }

    }

    fun searchArtist(artist: String, page: Int) : List<ArtistDto> {

        val url = URL(String.format(SEARCH_ARTIST, artist, page))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val searchArtist = gson.fromJson(it, SearchArtistDto::class.java)
                return searchArtist.data
            }

        }

    }

    fun searchTrackByName(trackName: String, page: Int) : List<TrackDto> {

        val url = URL(String.format(SEARCH_TRACK_BY_NAME, trackName, page))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val searchTopTrack = gson.fromJson(it, SearchTrackDto::class.java)
                return searchTopTrack.data
            }

        }

    }

    fun searchTrackById(trackId: String, page: Int) : List<TrackDto> {

        val url = URL(String.format(SEARCH_TRACK_BY_ID, trackId, page))

        with(url.openConnection() as HttpURLConnection){

            BufferedReader(InputStreamReader(inputStream)).use {

                val searchTopTrack = gson.fromJson(it, SearchTrackDto::class.java)
                return searchTopTrack.data
            }

        }

    }

}