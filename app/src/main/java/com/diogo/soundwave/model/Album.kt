package com.diogo.soundwave.model

data class Album(

    val name: String,
    val playCount: Int,
    val url: String,
    val image: List<Image>

)