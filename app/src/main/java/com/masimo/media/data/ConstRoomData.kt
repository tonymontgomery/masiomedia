package com.masimo.media.data


import com.masimo.media.R


object ConstRoomData {

    val notPlaying = Room(
        deviceId = 0,
        deviceName = "Not Playing",
        trackName = "select a device",
        albumName = "from Rooms screen",
        duration = "0:00",
        artistName = "",
        image = R.drawable.ic_album,
        artSmall = "",
        artLarge = "",
        isPlaying = false,
        userSelected = false
    )

    var mockedRoomList = mutableListOf<Room>(
        Room(
            deviceId = 100,
            deviceName = "Bedroom",
            trackName = "Eshghe Shirinam",
            albumName = "",
            duration = "2:53",
            artistName = "Ahllam",
            image = R.drawable.ahllam,
            artSmall = "",
            artLarge = "",
            isPlaying = false,
            userSelected = false
        ),
        Room(
            deviceId = 200,
            deviceName = "Lounge",
            trackName = "Party Life",
            albumName = "",
            duration = "3:13",
            artistName = "Deejay Al",
            image = R.drawable.party,
            artSmall = "",
            artLarge = "",
            isPlaying = true,
            userSelected = false
        ),
        Room(
            deviceId = 300,
            deviceName = "Kitchen",
            trackName = "Zakhare Asli",
            albumName = "",
            duration = "5:12",
            artistName = "Sohrab MJ",
            image = R.drawable.mj,
            artSmall = "",
            artLarge = "",
            isPlaying = true,
            userSelected = false
        ),
        Room(
            deviceId = 400,
            deviceName = "Patio",
            trackName = "Paeezi",
            albumName = "",
            duration = "3:22",
            artistName = "Satin",
            image = R.drawable.satin,
            artSmall = "",
            artLarge = "",
            isPlaying = false,
            userSelected = false
        ),
        Room(
            deviceId = 500,
            deviceName = "Garage",
            trackName = "Shadmehr",
            albumName = "",
            artistName = "Jehsas",
            duration = "3:48",
            image = R.drawable.shadmehr,
            artSmall = "",
            artLarge = "",
            isPlaying = true,
            userSelected = false
        )
    )
}
