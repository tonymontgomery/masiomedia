package com.masimo.media.ui.view.play


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.masimo.media.R
import com.masimo.media.data.ConstRoomData
import com.masimo.media.data.MainViewModel
import com.masimo.media.data.Room
import com.masimo.media.ui.theme.BackgroundOne
import com.masimo.media.ui.theme.BackgroundThree
import com.masimo.media.ui.theme.BackgroundTwo
import com.masimo.media.ui.view.room.NPRoomImage


@Composable
fun SongDescription(title: String, album: String, artist: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )

    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = artist, style = MaterialTheme.typography.body2, maxLines = 1, color = Color.White
        )
        Text(
            text = album, style = MaterialTheme.typography.body2, maxLines = 1, color = Color.White
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerSlider(currentRoom: Room) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Slider(
            value = 0f, onValueChange = {}, colors = SliderDefaults.colors(
                thumbColor = Color.White, activeTrackColor = Color.White
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "0:00", color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = currentRoom.duration, color = Color.White)
        }
    }
}

@Composable
fun PlayerButtons(
    room: Room,
    modifier: Modifier = Modifier,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 42.dp
) {

    var isPlaying by remember {
        mutableStateOf(room.isPlaying)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
        val buttonModifier = Modifier
            .size(sideButtonSize)
            .semantics { role = Role.Button }

        Image(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Skip Previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

        IconButton(onClick = {
            if (room == ConstRoomData.notPlaying) return@IconButton

            isPlaying = !isPlaying
            room.isPlaying = !room.isPlaying
        }) {
            Image(painter = painterResource(
                id = if (isPlaying) {
                    R.drawable.now_playing_controls_pause
                } else {
                    R.drawable.now_playing_controls_play
                }
            ),
                contentDescription = "Play Pause Button",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .size(playerButtonSize)
                    .semantics { role = Role.Button })

        }

        Image(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Skip Next",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )
    }
}

/**
 * NowPlayScreen
 * */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NowPlayScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundOne, BackgroundTwo, BackgroundThree
                    )
                )
            )
            .padding(horizontal = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = viewModel.selectedRoom.deviceName, color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            NPRoomImage(room = viewModel.selectedRoom)
            Spacer(modifier = Modifier.height(30.dp))
            SongDescription(
                viewModel.selectedRoom.trackName,
                viewModel.selectedRoom.artistName,
                viewModel.selectedRoom.albumName
            )
            Spacer(modifier = Modifier.height(35.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(30f)
            ) {
                PlayerSlider(viewModel.selectedRoom)
                Spacer(modifier = Modifier.height(40.dp))
                PlayerButtons(viewModel.selectedRoom, modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
