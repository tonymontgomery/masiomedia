package com.masimo.media.ui.view.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.masimo.media.data.Room

@Composable
fun RoomImage(room: Room, isSelected: Boolean) {
    Image(
        modifier = Modifier
            .alpha(if (isSelected) 1f else .3f)
            .padding(8.dp)
            .size(110.dp)
            .clip(CircleShape),
        painter = if (room.image != 0) {
            painterResource(id = room.image)
        } else {
            rememberAsyncImagePainter(room.artSmall)
        }, contentScale = ContentScale.Crop, contentDescription = null
    )
}
