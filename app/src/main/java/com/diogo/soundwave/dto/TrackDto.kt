package com.diogo.soundwave.dto

data class TrackDto(

    val id: String,
    val title_short: String,
    val md5_image: String,
    val duration: Float,
    val artist: ArtistIdDto,
    val album: AlbumIdDto

)