package com.diogo.soundwave.model

data class Track(

    val id: String,
    val name: String,
    val image: String,
    val duration: Float,
    val supArtist: () -> Artist

) {

    private lateinit var artist: Artist

    fun getArtist(): Artist {

        if (::artist.isInitialized)
            return this.artist

        this.artist = supArtist()
        return this.artist
    }

    override fun equals(other: Any?): Boolean {

        if (other !is Track)
            return false

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}