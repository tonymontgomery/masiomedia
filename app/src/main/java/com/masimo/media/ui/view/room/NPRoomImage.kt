package com.masimo.media.ui.view.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.masimo.media.data.Room

@Composable
fun NPRoomImage(room: Room) {

    Image(
        painter = if (room.image != 0) {
            painterResource(id = room.image)
        } else {
            rememberAsyncImagePainter(room.artLarge)
        },
        contentDescription = "Now playing track image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .sizeIn(maxWidth = 500.dp, maxHeight = 500.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    )
}
