package com.diogo.soundwave.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.diogo.soundwave.model.Artist
import com.diogo.soundwave.model.Player
import com.diogo.soundwave.model.Track
import com.diogo.soundwave.ui.theme.background
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun trackScreen(track: Track, player: Player){

    val artistRemember = remember { mutableStateOf<Artist?>(null) }

    LaunchedEffect(Unit) {
        artistRemember.value = withContext(Dispatchers.IO) {
            track.artist()
        }
    }

    val artist = artistRemember.value ?: return

    Column(
        modifier = Modifier
            .background(color = background),
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

    }

}