package com.masimo.media.ui.view.room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masimo.media.data.Room

@Composable
fun RoomCard(room: Room, isSelected: Boolean) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.weight(1f),
                Arrangement.Center) {
                //Device Name / Room Name
                Text(
                    text = room.deviceName,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                //Artist Name
                Text(
                    text = room.artistName,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                //Track Title
                Text(
                    text = room.trackName,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                )

                //Playstate
                Text(
                    modifier = Modifier.background(color = if (room.isPlaying) Color.Green else Color.Yellow),
                    text = if (room.isPlaying) "<<<PLAYING>>>" else "||| PAUSED |||",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Row {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Sharp.ArrowForward,
                            contentDescription = "Selected Room Icon",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(text = "Selected Room", style = typography.overline)
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = "Selected Room Icon",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
            RoomImage(room = room, isSelected)
        }
    }
}