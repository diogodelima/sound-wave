package com.diogo.soundwave

import com.diogo.soundwave.api.YouTubeApi
import org.junit.Assert.assertEquals
import org.junit.Test

class YouTubeApiTest {

    @Test
    fun searchTrackFromDrakeTest(){

        val api = YouTubeApi()
        val video = api.searchVideo("Best+I+Ever+Had", "Drake", 1)[0]
        val expected = "VideoDto(id=VideoIdDto(kind=youtube#video, videoId=Zfp3KfYH0xA))"

        assertEquals(expected, video.toString())
    }

}