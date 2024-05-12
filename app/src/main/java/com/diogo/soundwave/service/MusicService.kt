package com.diogo.soundwave.service

import com.diogo.soundwave.api.DeezerApi
import com.diogo.soundwave.api.YouTubeApi
import com.diogo.soundwave.dto.*
import com.diogo.soundwave.model.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlin.math.max

class MusicService(

    private val deezerApi: DeezerApi = DeezerApi(),
    private val youtubeApi: YouTubeApi = YouTubeApi()

) {

    fun searchArtist(artistId: String) : Artist {
        return dtoToArtist(deezerApi.searchArtist(artistId))
    }

    fun searchArtist(artistName: String, maxItems: Int) : Sequence<Artist> {
        return generateSequence(0) { n -> n + 1}
            .map { deezerApi.searchArtist(artistName, it) }
            .takeWhile { it.isNotEmpty() }
            .flatMap { it }
            .map(::dtoToArtist)
            .take(maxItems)
    }

    fun searchTrackByName(trackName: String, maxItems: Int) : Sequence<Track> {
        return generateSequence(0) { n -> n + 1}
            .map { deezerApi.searchTrackByName(trackName, it) }
            .takeWhile { it.isNotEmpty() }
            .flatMap { it }
            .map(::dtoToTrack)
            .take(maxItems)
    }

    fun initYouTube(youTubePlayerView: YouTubePlayerView, player: Player){
        youtubeApi.init(youTubePlayerView, player)
    }

    fun searchVideo(trackName: String, artistName: String) : () -> Video {
        return {
            dtoToVideo(
                youtubeApi.searchVideo(
                    trackName.replace(" ", "+"),
                    artistName.replace(" ", "+"),
                    1
                )[0]
            )
        }
    }

/*    fun searchTopArtistsByCountry(country: String, maxItems: Int) : Sequence<Artist> {
        return generateSequence(1) { n -> n + 1 }
            .map { lastFmApi.searchTopArtistsByCountry(country) }
            .takeWhile { it.isNotEmpty() }
            .flatMap { it }
            .map(::dtoToArtist)
            .take(maxItems)
    }

     fun searchTrack(artistName: String, trackName: String, maxItems: Int) : Sequence<Track> {
        return searchTracks(artistName, maxItems)
            .filter { track -> track.name == trackName }
    }

    private fun searchTracks(artistName: String, maxItems: Int) : Sequence<Track> {
        return generateSequence(1) { n -> n + 1 }
            .map { lastFmApi.searchTracks(artistName, it) }
            .takeWhile { it.isNotEmpty() }
            .flatMap { it }
            .map(::dtoToTrack)
            .take(maxItems)
    }

    private fun searchAlbums(artistName: String) : Sequence<Album> {
        return generateSequence(1) { n -> n + 1 }
            .map { lastFmApi.searchAlbums(artistName, it) }
            .takeWhile { it.isNotEmpty() }
            .flatMap { it }
            .map(::dtoToAlbum)
    }

*/
    private fun dtoToArtist(dto: ArtistDto) : Artist {
        return Artist(dto.id, dto.name, dto.picture_xl, dto.nb_fan, dto.nb_album)
    }

    private fun dtoToTrack(dto: TrackDto) : Track {
        return Track(dto.id, dto.title_short, dto.md5_image) {
            searchArtist(dto.artist.id)
        }
    }

    private fun dtoToVideo(dto: VideoDto) : Video {
        return Video(dto.id.kind, dto.id.videoId)
    }

/*
    private fun dtoToAlbum(dto: AlbumDto) : Album {
        return Album(dto.name, dto.playCount, dto.url, dto.image
            .map(::dtoToImage))
    }

    private fun dtoToImage(dto: ImageDto) : Image {
        return Image(dto.url, dto.size)
    }

    private fun dtoToTrack(dto: TrackDto) : Track {

        return Track(dto.name, dto.playCount, dto.listeners, dto.mbid, dto.url) {

            searchArtist(dto.artist.name, Int.MAX_VALUE)
                .first {
                    it.name == dto.artist.name
                }
        }

    }
*/

}