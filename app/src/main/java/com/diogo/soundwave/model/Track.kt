package com.diogo.soundwave.model

data class Track(

    val id: String,
    val name: String,
    val image: String,
    val artist: () -> Artist

)