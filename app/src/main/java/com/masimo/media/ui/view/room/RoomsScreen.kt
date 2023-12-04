package com.masimo.media.ui.view.room

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.masimo.media.data.MainViewModel


@Composable
fun RoomsScreen(viewModel: MainViewModel) {
    var selectedIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .selectableGroup()
    ) {
        items(viewModel.roomList.size) { index ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .selectable(selected = selectedIndex == index, onClick = {
                    selectedIndex = index
                    viewModel.selectedRoom = viewModel.roomList[index]
                    viewModel.selectedRoomID = viewModel.roomList[index].deviceId
                    viewModel.roomList[index].userSelected = true
                })

                .padding(16.dp),
                elevation = 2.dp,
                backgroundColor = if (viewModel.selectedRoomID == viewModel.roomList[index].deviceId) Color.Green else Color.DarkGray,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))) {

                Row {
                    val isSelected = viewModel.selectedRoomID == viewModel.roomList[index].deviceId
                    RoomCard(room = viewModel.roomList[index], isSelected)
                }
            }
        }
    }
}


