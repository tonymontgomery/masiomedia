package com.masimo.media.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var roomList = mutableListOf<Room>()

    var mockDataOn: Boolean = false

    var selectedRoomID: Int = -1
    var selectedRoom: Room = ConstRoomData.notPlaying

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(1)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Rooms", "Player", "Settings")

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }
}
