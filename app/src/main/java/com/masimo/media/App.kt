package com.masimo.media


import android.app.Application
import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.masimo.media.data.ConstRoomData
import com.masimo.media.data.MainViewModel
import com.masimo.media.data.Room
import org.json.JSONObject


class App : Application() {
    private val baseUrl = "https://skyegloup-eula.s3.amazonaws.com/heos_app/code_test/"
    private val devicesUrl = "devices.json"
    private val npURL = "nowplaying.json"
    var request2: StringRequest? = null

    override fun onCreate() {
        super.onCreate()
        application = this

        appContext = applicationContext
    }

    fun fetchRoomsList(viewModel: MainViewModel, newRoomList: MutableList<Room>, context: Context) {
        newRoomList.clear()

        if (viewModel.mockDataOn) {
            newRoomList.addAll(ConstRoomData.mockedRoomList)
        } else {
            val queue = Volley.newRequestQueue(context)
            val listTest = mutableListOf<Room>()

            val request1 = StringRequest(Request.Method.GET, baseUrl + devicesUrl, { response ->
                val data = response.toString()
                val jObject = JSONObject(data)
                val devicesArray = jObject.getJSONArray("Devices")
                for (i in 0 until devicesArray.length()) {
                    val device = devicesArray.getJSONObject(i)
                    val deviceName: Any = device.get("Name")
                    val deviceID: Any = device.get("ID")

                    val newItem = Room(
                        deviceName = deviceName.toString(),
                        deviceId = deviceID as Int,
                        trackName = "",
                        albumName = "",
                        duration = "0:00",
                        artistName = "",
                        isPlaying = false, //playing/paused
                        image = 0,
                        artSmall = "",
                        artLarge = "",
                        userSelected = false
                    )

                    listTest.add(i, newItem)
                }

                queue.add(request2)

            }, { })

            request2 = StringRequest(Request.Method.GET, baseUrl + npURL, { response ->
                val data = response.toString()
                val jObject = JSONObject(data)
                val npDevicesArray = jObject.getJSONArray("Now Playing")

                for (i in 0 until npDevicesArray.length()) {

                    val npDevice = npDevicesArray.getJSONObject(i)
                    val deviceID: Any = npDevice.get("Device ID")
                    val artSmall: String = npDevice.getString("Artwork Small")
                    val artLarge: String = npDevice.getString("Artwork Large")
                    val trackName: String = npDevice.getString("Track Name")
                    val albumName: String = ""
                    val artistName: String = npDevice.getString("Artist Name")

                    val newNPItem = Room(
                        deviceName = listTest[i].deviceName,
                        deviceId = deviceID as Int,
                        trackName = trackName,
                        albumName = albumName,
                        duration = "0:00",
                        artistName = artistName,
                        isPlaying = false,
                        image = 0,
                        artSmall = artSmall,
                        artLarge = artLarge,
                        userSelected = false
                    )

                    newRoomList.add(i, newNPItem)
                }
            }, { })

            queue.add(request1)
        }
    }

    companion object {
        lateinit  var appContext: Context

        lateinit var application: Application
            private set
    }
}