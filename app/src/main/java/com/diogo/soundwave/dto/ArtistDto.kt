package com.diogo.soundwave.dto

data class ArtistDto(

    val id: String,
    val name: String,
    val picture: String,
    val picture_medium: String,
    val picture_xl: String,
    val nb_fan: Int,
    val nb_album: Int

)