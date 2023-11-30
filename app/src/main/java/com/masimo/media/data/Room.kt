package com.masimo.media.data

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Room(
    override var deviceId: Int = 0,
    var deviceName: String,
    var trackName: String,
    var albumName: String,
    var duration: String,
    var artistName: String,
    var isPlaying: Boolean,
    @DrawableRes val image: Int,
    var artSmall: String,
    var artLarge: String,
    var userSelected: Boolean
) : ListAdapterItem, Serializable
