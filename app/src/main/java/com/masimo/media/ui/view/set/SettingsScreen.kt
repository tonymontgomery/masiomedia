package com.masimo.media.ui.view.set

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.masimo.media.App
import com.masimo.media.data.MainViewModel


@Composable
fun SettingsScreen(viewModel: MainViewModel) {

    var switchOn by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            modifier = Modifier.scale(scale = 2f),
            checked = viewModel.mockDataOn,
            onCheckedChange = { _ ->
                switchOn = !switchOn
                viewModel.mockDataOn = !viewModel.mockDataOn
                App().fetchRoomsList(viewModel, viewModel.roomList, App.appContext)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green, checkedTrackColor = Color.Blue
            )
        )

        Text(
            text = if (switchOn) "MOCK DATA (ON)" else "MOCK DATA (OFF)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
