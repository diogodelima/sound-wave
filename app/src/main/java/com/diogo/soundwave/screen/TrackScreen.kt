package com.diogo.soundwave.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.diogo.soundwave.R
import com.diogo.soundwave.model.Artist
import com.diogo.soundwave.model.Player
import com.diogo.soundwave.model.Track
import com.diogo.soundwave.screen.components.MusicBar
import com.diogo.soundwave.ui.theme.playButton
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun trackScreen(track: Track, player: Player){

    val playerTrack by player.playerTrack.playerTrackData.observeAsState()
    val artistRemember = remember { mutableStateOf<Artist?>(null) }

    LaunchedEffect(Unit) {

        artistRemember.value = withContext(Dispatchers.IO) {
            track.getArtist()
        }
    }

    val artist = artistRemember.value ?: return

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = {}
            ){
                Icon(
                    Icons.Rounded.ArrowBack, contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = artist.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            IconButton(
                onClick = {}
            ){
                Icon(
                    Icons.Rounded.MoreVert, contentDescription = "MoreVert",
                    tint = Color.White
                )
            }

        }

        AsyncImage(
            modifier = Modifier
                .size(300.dp)
                .padding(vertical = 30.dp),
            model = artist.picture,
            contentDescription = "Artist Image"
        )

        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = track.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Text(
                        text = artist.name,
                        color = Color.White,
                        fontSize = 20.sp
                    )

                }

                IconButton(
                    onClick = {}
                ){
                    Icon(
                        Icons.Rounded.FavoriteBorder, contentDescription = "Favourite Border",
                        tint = Color.White
                    )
                }

            }

        }

        val width = remember { mutableIntStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .onGloballyPositioned {
                    width.intValue = it.size.width
                }
        ){

            Column {

                playerTrack?.getCurrentSecond(track)?.let {
                    MusicBar(
                        width = width.intValue.dp,
                        indicatorValue = it,
                        maxIndicatorValue = track.duration
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    playerTrack?.getCurrentSecond(track)?.let {

                        Text(
                            text = if (it > track.duration) formatSeconds(track.duration) else formatSeconds(it),
                            color = Color.White,
                            fontSize = 12.sp,
                        )

                    }

                    Text(
                        text = formatSeconds(track.duration),
                        color = Color.White,
                        fontSize = 12.sp,
                    )

                }

            }

        }

        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            IconButton(
                modifier = Modifier
                    .size(40.dp),
                onClick = {}
            ){
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(R.drawable.skip_previous),
                    contentDescription = "Favourite Border",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

            IconButton(
                modifier = Modifier
                    .size(80.dp),
                onClick = {

                    if (!player.playerTrack.isInitializated())
                        return@IconButton

                    if (playerTrack?.isPlaying(track) == true)
                        player.playerTrack.youTubePlayer.pause()
                    else {
                        GlobalScope.launch {
                            player.play(track)
                        }
                    }

                }
            ){
                Icon(
                    modifier = Modifier
                        .size(80.dp),
                    painter = if (playerTrack?.isPlaying(track) == true) painterResource(R.drawable.pause_icon) else painterResource(R.drawable.play_icon),
                    contentDescription = if (playerTrack?.isPlaying(track) == true) "Pause" else "Play",
                    tint = playButton
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

            IconButton(
                modifier = Modifier
                    .size(40.dp),
                onClick = {}
            ){
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(R.drawable.skip_next),
                    contentDescription = "Favourite Border",
                    tint = Color.White
                )
            }

        }

    }

}

private fun formatSeconds(seconds: Float) : String {

    val m = (seconds / 60).toInt()
    val s = (seconds % 60).toInt()

    return String.format("%02d:%02d", m, s)
}