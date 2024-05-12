package com.diogo.soundwave

import com.diogo.soundwave.api.DeezerApi
import org.junit.Assert.assertEquals
import org.junit.Test

class DeezerApiTest {

    private val api = DeezerApi()

    @Test
    fun searchArtistTest(){

        val actual = api.searchArtist("Drake", 0).first()

        println(actual.toString())
    }

    @Test
    fun searchTrackTest(){

        val actual = api.searchTrackByName("Farda", 0).first()

        println(actual.toString())
    }

}